package com.txsoft.constructioncalculator.ui.main.calculation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.FragmentCalculationBinding
import com.txsoft.constructioncalculator.models.Model
import com.txsoft.constructioncalculator.models.Shape
import com.txsoft.constructioncalculator.models.Unit
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.InvalidInputType
import com.txsoft.constructioncalculator.models.enums.InvalidInputType.*
import com.txsoft.constructioncalculator.models.enums.Material
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.enums.Params.*
import com.txsoft.constructioncalculator.models.getParamsValuesMap
import com.txsoft.constructioncalculator.ui.DELAY_TIME_SCROLLING
import com.txsoft.constructioncalculator.ui.main.AdapterRecyclerShapes
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class CalculationFragment : Fragment() {

    private lateinit var formSelected: Form
    private var scenarioByLength: Boolean = true
    private lateinit var materialSelected: Material
    private lateinit var binding: FragmentCalculationBinding
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var adapterRecyclerInput: AdapterRecyclerInput
    private var jobDelayScrolling: Job? = null
    private lateinit var inputMap: HashMap<Params, Double?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        calcViewModel = ViewModelProvider(this).get(CalcViewModel::class.java)
        initData()
        binding = FragmentCalculationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.calcViewModel = this.calcViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerShapesMarked()
        initRecyclerInputFields()
        initSpinnerMaterial()
        initBtnCalculate()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        jobDelayScrolling?.cancel()
    }

    @Suppress("ReplaceWithEnumMap")
    private fun initData() {

        inputMap = hashMapOf()
        formSelected = Form.PIPE
        materialSelected = Material.ALUMINIUM
        arguments?.let {
            val formName = CalculationFragmentArgs.fromBundle(it).form
            val materialName = CalculationFragmentArgs.fromBundle(it).material
            formSelected = Form.valueOf(formName)
            materialSelected = Material.valueOf(materialName)
        }
        calcViewModel.setForm(formSelected)
        calcViewModel.setScenario(scenarioByLength)
        calcViewModel.setMaterial(materialSelected)
    }

    private fun initRecyclerShapesMarked() {

        val forms = Form.values().toCollection(arrayListOf())
        val adapterRecyclerShapes = AdapterRecyclerShapes(
            requireContext(), forms,
            inCalculation = false,
            marked = true
        ) {
        }
        recycler_shapes_marked.apply {
            setHasFixedSize(true)
            indicator.attachToRecyclerView(this)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            attachSnapHelperWithListener(
                PagerSnapHelper(),
                onSnapPositionChangeListener = object : OnSnapPositionChangeListener {
                    override fun onSnapPositionChange(position: Int) {
                        calcViewModel.setForm(forms[position])
                    }
                })
            adapter = adapterRecyclerShapes
            scrollToPosition(forms.indexOf(formSelected))
        }
    }

    private fun initRecyclerInputFields() {

        adapterRecyclerInput =
            AdapterRecyclerInput(requireContext(), formSelected, scenarioByLength) { param, value ->
                inputMap[param] = value
                val map = HashMap(inputMap)
                val ready =
                    !map.values.contains(0.0) && !map.apply { remove(COUNT) }.values.contains(null)
                calcViewModel.setReadyForCalculation(ready)
            }
        recycler_input.apply {
            setHasFixedSize(false)
            adapter = adapterRecyclerInput
        }
    }

    private fun initSpinnerMaterial() {

        val adapterSpinner = AdapterSpinner(
            requireContext(), materials = Material.values(), metric = Unit.METRIC
        )
        spinner_material.apply {
            adapter = adapterSpinner
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                }
            }
            setSelection(Material.values().indexOf(materialSelected))
        }
    }

    private fun initBtnCalculate() {

        btn_calculate.setOnClickListener {
            adapterRecyclerInput.setResultMap(inputMap)
            calcViewModel.isReadyForCalculation().value?.let {
                if (it)
                    calculate()
                else
                    Snackbar.make(binding.root, "Not all parameters are set for computation!", 2200)
                        .show()
            }
        }
    }

    private fun calculate() {

        val shape: Shape = when (formSelected) {

            Form.T_BAR -> Shape.TBar(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness = inputMap[THICKNESS]!!
            )

            Form.SQUARE_TUBE -> Shape.SquareTube(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness = inputMap[THICKNESS]!!
            )

            Form.SQUARE_BAR -> Shape.SquareBar(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                side = inputMap[SIDE]!!
            )

            Form.PIPE -> Shape.Pipe(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                thickness = inputMap[THICKNESS]!!,
                diameter = inputMap[DIAMETER]!!
            )

            Form.ROUND_BAR -> Shape.RoundBar(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                diameter = inputMap[DIAMETER]!!
            )

            Form.HEXAGONAL_TUBE -> Shape.HexagonalTube(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                side = inputMap[SIDE]!!,
                diameter = inputMap[DIAMETER]!!
            )

            Form.HEXAGONAL_HEX -> Shape.HexagonalHex(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                thickness = inputMap[THICKNESS]!!
            )

            Form.HEXAGONAL_BAR -> Shape.HexagonalBar(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                height = inputMap[HEIGHT]!!
            )

            Form.FLAT_BAR -> Shape.FlatBar(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!
            )

            Form.CHANNEL -> Shape.Channel(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness = inputMap[THICKNESS]!!
            )

            Form.BEAM -> Shape.Beam(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness1 = inputMap[THICKNESS_1]!!,
                thickness2 = inputMap[THICKNESS_2]!!
            )

            Form.ANGLE -> Shape.Angle(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness = inputMap[THICKNESS]!!
            )

            else -> Shape.Angle(
                length = if (scenarioByLength) inputMap[LENGTH] else null,
                width = inputMap[WIDTH]!!,
                height = inputMap[HEIGHT]!!,
                thickness = inputMap[THICKNESS]!!
            )
        }
        val model: Model? = if (scenarioByLength)
            Model.createByLength(shape, materialSelected)
        else
            Model.createByWeight(shape, materialSelected, inputMap[WEIGHT]!!)

        if (model != null)
            openBottomSheetDialogResult(model)
        else Snackbar.make(binding.root, "Something went wrong, try again!", 2200)
            .show()

    }

    private fun initObservers() {

        calcViewModel.getForm().observe(viewLifecycleOwner, Observer {
            it?.let {
                formSelected = it
                jobDelayScrolling = CoroutineScope(Main).launch {
                    delay(DELAY_TIME_SCROLLING)
                    inputMap = getParamsValuesMap(it, scenarioByLength)
                    adapterRecyclerInput.setFormSelected(it)
                }
            }
        })

        calcViewModel.getScenario().observe(viewLifecycleOwner, Observer { byLength ->
            scenarioByLength = byLength
            if (byLength)
                inputMap.remove(WEIGHT)
            else
                inputMap.remove(LENGTH)

            jobDelayScrolling = GlobalScope.launch(Main) {
                delay(DELAY_TIME_SCROLLING)
                adapterRecyclerInput.setScenario(byLength)
            }
        })
    }

    private fun openBottomSheetDialogResult(model: Model) {

        val bsd = BottomSheetDialog(requireContext(), R.style.bottomSheetDialog)
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.bottom_sheet_dialog, lay_bottom_sheet_container)
        val btnSave: AppCompatTextView = view.findViewById(R.id.tv_save_res)
        val btnShare: AppCompatTextView = view.findViewById(R.id.tv_share_res)
        val tvLengthOrWeight: AppCompatTextView = view.findViewById(R.id.tv_res_weight_or_length)
        val tvVolume: AppCompatTextView = view.findViewById(R.id.tv_res_volume)
        val tvSurface: AppCompatTextView = view.findViewById(R.id.tv_res_surface)
        val ivShape: AppCompatImageView = view.findViewById(R.id.iv_res_shape)

        ivShape.setImageDrawable(
            ContextCompat.getDrawable(requireContext(), model.shape.form.imageRes)
        )
        val textLengthOrWeight =
            if (scenarioByLength) getString(R.string.LENGTH) + " : " + model.weight.toString()
            else getString(R.string.WEIGHT) + " : " + model.shape.length.toString()
        tvLengthOrWeight.text = textLengthOrWeight

        val volume = getString(R.string.VOLUME) + " : " + model.shape.volume.toString()
        tvVolume.text = volume
        btnSave.setOnClickListener {
            bsd.dismiss()
        }
        btnShare.setOnClickListener {
            bsd.dismiss()
        }
        bsd.setContentView(view)
        bsd.show()
    }

}
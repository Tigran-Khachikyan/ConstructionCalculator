package com.txsoft.constructioncalculator.ui.main.calculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.FragmentCalculationBinding
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
    private var invalidInputMap: HashMap<Params, InvalidInputType>? = null

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

        adapterRecyclerInput = AdapterRecyclerInput(formSelected, scenarioByLength, this::onInput)
        recycler_input.apply {
            setHasFixedSize(false)
            adapter = adapterRecyclerInput
        }
    }

    private fun onInput(param: Params, value: Double?) {

        inputMap[param] = value
        val map = inputMap
        val ready = !map.values.contains(0.0) && !map.apply { remove(COUNT) }.values.contains(null)
        calcViewModel.setReadyForCalculation(ready)
    }


    private fun getInvalidInput(map: HashMap<Params, Double?>): HashMap<Params, InvalidInputType>? {

        invalidInputMap = null
        map.forEach { entry ->
            @Suppress("ReplaceWithEnumMap")
            val type = when (entry.value) {
                null -> {
                    if (invalidInputMap == null) invalidInputMap = hashMapOf()
                    NULL
                }
                0.0 -> {
                    if (invalidInputMap == null) invalidInputMap = hashMapOf()
                    ZERO
                }
                else -> null
            }
            type?.let { invalidInputMap!![entry.key] = type }
        }
        return invalidInputMap
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
            val invalidInputMap = getInvalidInput(inputMap)
            invalidInputMap?.let {
                Snackbar.make(binding.root, "Not all params are completed!", 3000)
                adapterRecyclerInput.setInvalidFields(it)
            } ?: calculate()
        }
    }

    private fun calculate() {

        Toast.makeText(requireContext(), "calculation", Toast.LENGTH_LONG).show()
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

        calcViewModel.getScenario().observe(viewLifecycleOwner, Observer {
            scenarioByLength = it
            jobDelayScrolling = GlobalScope.launch(Main) {
                delay(DELAY_TIME_SCROLLING)
                adapterRecyclerInput.setScenario(it)
            }
        })

    }


}
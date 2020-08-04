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
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.FragmentCalculationBinding
import com.txsoft.constructioncalculator.models.Unit
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.InvalidInputType
import com.txsoft.constructioncalculator.models.enums.InvalidInputType.*
import com.txsoft.constructioncalculator.models.enums.Material
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.enums.Params.*
import com.txsoft.constructioncalculator.ui.DELAY_TIME_SCROLLING
import com.txsoft.constructioncalculator.ui.main.AdapterRecyclerShapes
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class CalculationFragment : Fragment() {

    private var formSelected: Form? = null
    private var materialSelected: Material? = null
    private lateinit var binding: FragmentCalculationBinding
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var adapterRecyclerInput: AdapterRecyclerInput
    private var jobDelayScrolling: Job? = null
    private var inputMap: HashMap<Params, Double?>? = null
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


    private fun initData() {
        arguments?.let {
            val formName = CalculationFragmentArgs.fromBundle(it).form
            val materialName = CalculationFragmentArgs.fromBundle(it).material
            formSelected = Form.valueOf(formName)
            materialSelected = Material.valueOf(materialName)
            calcViewModel.setForm(formSelected)
        }
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

        adapterRecyclerInput = AdapterRecyclerInput(this::getInputParams)
        recycler_input.apply {
            setHasFixedSize(false)
            adapter = adapterRecyclerInput
        }
    }

    private fun getInputParams(map: HashMap<Params, Double?>) {

        inputMap = map
        val btnBackgroundRes =
            if (map.values.contains(0.0) || map.apply { remove(COUNT) }.values.contains(null)) R.drawable.back_button_calc else R.drawable.back_button_calc_ready
        btn_calculate.background = resources.getDrawable(btnBackgroundRes, null)
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
            val invalidInputMap = inputMap?.let { getInvalidInput(it) }
            invalidInputMap?.let {
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
                jobDelayScrolling = CoroutineScope(Main).launch {
                    delay(DELAY_TIME_SCROLLING)
                    adapterRecyclerInput.setFormSelected(it)
                }
            }
        })

        calcViewModel.getScenario().observe(viewLifecycleOwner, Observer {
            jobDelayScrolling = GlobalScope.launch(Main) {
                delay(DELAY_TIME_SCROLLING)
                adapterRecyclerInput.setScenario(it)
            }
        })

    }


}
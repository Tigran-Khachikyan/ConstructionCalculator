package com.txsoft.constructioncalculator.ui.main.calculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.FragmentCalculationBinding
import com.txsoft.constructioncalculator.models.Unit
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.Material
import com.txsoft.constructioncalculator.ui.main.shapes.AdapterRecyclerShapes
import com.txsoft.constructioncalculator.ui.main.shapes.ShapeFragmentDirections
import kotlinx.android.synthetic.main.fragment_calculation.*

class CalculationFragment : Fragment() {

    private var form: Form? = null
    private var material: Material? = null
    private lateinit var binding: FragmentCalculationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initData()
        binding = FragmentCalculationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerShapesMarked()
        initSpinnerMaterial()
    }


    private fun initData() {
        arguments?.let {
            val formName = CalculationFragmentArgs.fromBundle(it).form
            val materialName = CalculationFragmentArgs.fromBundle(it).material
            form = Form.valueOf(formName)
            material = Material.valueOf(materialName)
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
                    }
                })
            adapter = adapterRecyclerShapes
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
        }
    }

}
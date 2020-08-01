package com.txsoft.constructioncalculator.ui.main.materials

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.Unit
import com.txsoft.constructioncalculator.models.enums.Material
import com.txsoft.constructioncalculator.models.enums.MaterialType
import com.txsoft.constructioncalculator.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_materials_pager.*

class MaterialsPagerFragment(private val _type: MaterialType) : Fragment() {

    private lateinit var materials: ArrayList<Material>
    private lateinit var activity: MainActivity
    private var parent: MaterialsContainerFragment? = null
    private val inCalculation by lazy {
        parent?.form != null && !parent?.form.equals("default")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
        parent = parentFragment as MaterialsContainerFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materials = Material.values().filter { it.type == _type }.toCollection(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_materials_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapterRecyclerMaterials =
            AdapterRecyclerMaterials(activity, materials, Unit.METRIC, inCalculation) {
                parent?.startCalculation(it.name)
            }
        recycler_materials.apply {
            adapter = adapterRecyclerMaterials
            setHasFixedSize(true)
        }
    }

}
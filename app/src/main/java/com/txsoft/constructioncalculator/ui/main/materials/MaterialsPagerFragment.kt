package com.txsoft.constructioncalculator.ui.main.materials

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.Form
import com.txsoft.constructioncalculator.models.Material
import com.txsoft.constructioncalculator.models.MaterialType
import com.txsoft.constructioncalculator.ui.main.MainActivity
import com.txsoft.constructioncalculator.ui.main.shapes.AdapterRecyclerShapes
import kotlinx.android.synthetic.main.fragment_materials_pager.*
import kotlinx.android.synthetic.main.fragment_shape_pager.*

class MaterialsPagerFragment(private val _type: MaterialType) : Fragment() {

    private lateinit var materials: ArrayList<Material>
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
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
        val adapterRecyclerMaterials = AdapterRecyclerMaterials(activity, materials) {
        }
        recycler_materials.apply {
            adapter = adapterRecyclerMaterials
            setHasFixedSize(true)
        }
    }

}
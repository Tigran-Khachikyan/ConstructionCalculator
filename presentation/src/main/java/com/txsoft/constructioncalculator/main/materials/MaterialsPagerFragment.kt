package com.txsoft.constructioncalculator.main.materials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.IMaterial
import com.txsoft.constructioncalculator.models.Unit
import kotlinx.android.synthetic.main.fragment_materials_pager.*

class MaterialsPagerFragment(private val materials: List<IMaterial>?) : Fragment() {

    private var parent: MaterialsContainerFragment? = null
    private val inCalculation by lazy {
        parent?.form != null && !parent?.form.equals("default")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        parent = parentFragment as MaterialsContainerFragment
        return inflater.inflate(R.layout.fragment_materials_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        val adapterRecMaterials = AdapterRecMaterials( materials, Unit.METRIC, inCalculation) {
                parent?.startCalculation(it.name)
            }
        recycler_materials.apply {
            adapter = adapterRecMaterials
            setHasFixedSize(true)
        }
    }

}
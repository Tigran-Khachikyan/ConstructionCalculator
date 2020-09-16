package com.txsoft.constructioncalculator.main.materials

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.main.MainActivity
import com.txsoft.constructioncalculator.models.IMaterial
import com.txsoft.domain.use_cases.MaterialsInflater
import kotlinx.android.synthetic.main.fragment_materials_container.*


class MaterialsContainerFragment : Fragment() {

    private lateinit var materialsViewModel: MaterialsViewModel
    private val resource: OnResourceInflater<IMaterial> by lazy { MaterialsInflater(requireContext()) }

    private lateinit var mainActivity: MainActivity
    val form by lazy {
        arguments?.let {
            MaterialsContainerFragmentArgs.fromBundle(it).shapeSelected
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        materialsViewModel = ViewModelProvider(this).get(MaterialsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_materials_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = MaterialPagerAdapter(resource, childFragmentManager)
        pagerMaterials.adapter = sectionsPagerAdapter
        tabsMaterials.setupWithViewPager(pagerMaterials)
    }

    fun startCalculation(_material: String) {
        form?.let {
            val direction =
                MaterialsContainerFragmentDirections.actionStartCalculation(it, _material)
            findNavController().navigate(direction)
        }
    }


/*    val fab: FloatingActionButton = findViewById(R.id.fab)
    fab.setOnClickListener { view ->
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }*/
}
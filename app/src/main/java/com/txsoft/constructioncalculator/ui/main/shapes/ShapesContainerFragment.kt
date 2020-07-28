package com.txsoft.constructioncalculator.ui.main.shapes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_shapes_container.*

class ShapesContainerFragment : Fragment() {

    private lateinit var materialsViewModel: ShapesViewModel
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        materialsViewModel = ViewModelProvider(this).get(ShapesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_shapes_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapterShapes(mainActivity, childFragmentManager)
        view_pager_shapes.adapter = sectionsPagerAdapter
        tabs_shapes.setupWithViewPager(view_pager_shapes)
    }
}
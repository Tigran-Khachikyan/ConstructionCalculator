package com.txsoft.constructioncalculator.ui.main.shapes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.Form
import com.txsoft.constructioncalculator.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_materials_pager.*
import kotlinx.android.synthetic.main.fragment_shape_pager.*

class ShapePagerFragment(private val _build: Boolean) : Fragment() {

    private lateinit var forms: ArrayList<Form>
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forms = Form.values().filter { it.build == _build }.toCollection(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shape_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapterRecyclerShapes = AdapterRecyclerShapes(activity, forms) {
        }
        recycler_shapes.apply {
            setHasFixedSize(true)
            adapter = adapterRecyclerShapes
        }
    }

}
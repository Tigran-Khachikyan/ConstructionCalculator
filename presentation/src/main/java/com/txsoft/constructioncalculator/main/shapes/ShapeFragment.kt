package com.txsoft.constructioncalculator.main.shapes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.FORM_TYPE_CONSTRUCTION
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.main.AdapterRecyclerShapes
import com.txsoft.constructioncalculator.main.MainActivity
import com.txsoft.constructioncalculator.models.IForm
import com.txsoft.constructioncalculator.models.IMaterial
import kotlinx.android.synthetic.main.fragment_shape.*

class ShapeFragment() : Fragment() {

    private lateinit var activity: MainActivity
    private lateinit var resource: OnResourceInflater<IForm>
    private val inCalculation by lazy {
        arguments?.let {
            ShapeFragmentArgs.fromBundle(it).calculation
        } ?: false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shape, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_shapes_const.init(CONSTRUCTION)
        recycler_shapes_geom.init(GEOMETRIC)
    }


    private fun RecyclerView.init(type: String) {
        val forms = getFormsTypedList(type)
        val adapterRecyclerShapes =
            AdapterRecyclerShapes(activity, forms, inCalculation, marked = false) {

                findNavController().navigate(ShapeFragmentDirections.actionPassShape(it.name))
            }
        setHasFixedSize(true)
        adapter = adapterRecyclerShapes
    }


    private fun getFormsTypedList(type: String): List<IForm>? {
        return resource.getTypedResourceMap()[type]
    }

}
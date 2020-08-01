package com.txsoft.constructioncalculator.ui.main.calculation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.HolderMaterialRecyclerBinding
import com.txsoft.constructioncalculator.models.Unit
import com.txsoft.constructioncalculator.models.enums.Material


class AdapterSpinner(
    context: Context,
    res: Int = R.layout.holder_material_recycler,
    materials: Array<Material>,
    metric: Unit
) :
    ArrayAdapter<Material>(context, res, materials) {

    private val resource = res
    var unitSelected = metric
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            HolderMaterialRecyclerBinding.inflate(LayoutInflater.from(context), parent, false)
        val side = context.resources.getDimension(R.dimen.material_holder_icon_small).toInt()
        binding.icMaterial.layoutParams = ConstraintLayout.LayoutParams(side,side)
        val mat = getItem(position)
        mat?.run {
            binding.tvMaterialRec.text = context.getString(nameRes)
            val densityText = context.getString(R.string.density) + " : " + density
            binding.tvDensityMaterialRec.text = densityText
        }
        return binding.root
    }
}

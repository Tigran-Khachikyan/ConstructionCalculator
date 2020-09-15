package com.txsoft.constructioncalculator.main.materials

import android.content.Context
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.HolderMaterialRecyclerBinding
import com.txsoft.constructioncalculator.models.enums.Material


class AdapterRecyclerMaterials(
    private val context: Context,
    private val materials: List<Material>?,
    private var unit: com.txsoft.constructioncalculator.models.Unit,
    private val inCalculation: Boolean,
    private val func: (Material) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerMaterials.Holder>() {

    inner class Holder(private val binding: HolderMaterialRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(material: Material) {
            binding.apply {
                tvMaterialRec.text = context.getString(material.nameRes)
                val densityText = context.getString(R.string.density) + " : " + material.density
                tvDensityMaterialRec.text = densityText
                val side = context.resources.getDimension(R.dimen.material_holder_icon_large).toInt()
                binding.icMaterial.layoutParams = ConstraintLayout.LayoutParams(side,side)
                root.apply {
                    isFocusable = inCalculation
                    isClickable = inCalculation
                    if (inCalculation) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            val outValue = TypedValue()
                            context.theme.resolveAttribute(
                                android.R.attr.selectableItemBackground, outValue, true
                            )
                            foreground = context.getDrawable(outValue.resourceId)
                        }
                    }
                    setOnClickListener {
                        if (inCalculation)
                            materials?.run { func(get(adapterPosition)) }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            HolderMaterialRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = materials?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        materials?.run {
            holder.bind(get(position))
        }
    }
}

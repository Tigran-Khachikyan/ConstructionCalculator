package com.txsoft.constructioncalculator.ui.main.materials

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.HolderMaterialRecyclerBinding
import com.txsoft.constructioncalculator.models.Material


class AdapterRecyclerMaterials(
    private val context: Context,
    private val materials: List<Material>?,
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

package com.txsoft.constructioncalculator.ui.main.calculation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.HolderInputBinding
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.getParamsArray


class AdapterRecyclerInput(
    private val context: Context,
    private val form: Form,
    private val byLength: Boolean,
    private val func: (Form) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerInput.Holder>() {

    private var fields: Array<Params> = getParamsArray(form, byLength)

    inner class Holder(private val binding: HolderInputBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(param: Params) {
            binding.layInputField.hint = context.getString(param.nameRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = HolderInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = fields.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(fields[position])
    }


}

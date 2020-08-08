@file:Suppress("ReplaceWithEnumMap")

package com.txsoft.constructioncalculator.ui.main.calculation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.HolderInputBinding
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.InvalidInputType
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.enums.Params.*
import com.txsoft.constructioncalculator.models.getParamsArray


class AdapterRecyclerInput(
    private val context: Context,
    private var form: Form,
    private var byLength: Boolean,
    private val onParamInputChanged: (Params, Double?) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerInput.Holder>() {

    private var fields: List<Params>
    private var map: HashMap<Params, Double?>

    init {
        fields = getParamsArray(form, byLength)
        map = hashMapOf()
    }

    fun setResultMap(_map: HashMap<Params, Double?>) {
        map = _map
        notifyDataSetChanged()
    }

    fun setFormSelected(form: Form) {
        this.form = form
        fields = getParamsArray(form, byLength)
        map.clear()
        notifyDataSetChanged()
    }

    fun setScenario(byLength: Boolean) {
        this.byLength = byLength
        fields = getParamsArray(form, byLength)

        if (byLength) {
            map.remove(WEIGHT)
            map[LENGTH] = -1.0
        } else {
            map.remove(LENGTH)
            map[WEIGHT] = -1.0
        }
        notifyDataSetChanged()
    }


    inner class Holder(private val binding: HolderInputBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(param: Params) {

            binding.apply {
                if (map.isNotEmpty()) {
                    map.keys.forEach { par ->
                        if (par == param) {
                            when (val value = map[par]) {
                                null -> {
                                    if (par != COUNT) {
                                        layInputField.isErrorEnabled = true
                                        layInputField.error =
                                            context.resources.getString(R.string.invalid_null)
                                    }
                                    etInputField.setText("")
                                }
                                0.0 -> {
                                    layInputField.isErrorEnabled = true
                                    layInputField.error =
                                        context.resources.getString(R.string.invalid_0)
                                    etInputField.setText("0.0")
                                }
                                -1.0 -> {
                                    layInputField.isErrorEnabled = false
                                    etInputField.setText("")
                                }
                                else -> {
                                    layInputField.isErrorEnabled = false
                                    etInputField.setText(value.toString())
                                }
                            }
                        }
                    }
                } else {
                    etInputField.text?.clear()
                    layInputField.isErrorEnabled = false
                }

                layInputField.hint = context.getString(param.nameRes)
                etInputField.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(input: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        input: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                        val curParam = fields[layoutPosition]
                        val value = input?.let {
                            if (it.toString() != "" && it.toString() != "-1.0") it.toString()
                                .toDouble() else null
                        }
                        onParamInputChanged(curParam, value)
                    }
                })
            }
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

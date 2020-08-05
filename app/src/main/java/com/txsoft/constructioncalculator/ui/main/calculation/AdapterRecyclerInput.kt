package com.txsoft.constructioncalculator.ui.main.calculation

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.txsoft.constructioncalculator.databinding.HolderInputBinding
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.InvalidInputType
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.getParamsArray


class AdapterRecyclerInput(
    private var form: Form,
    private var byLength: Boolean,
    private val onInput: (Params, Double?) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerInput.Holder>() {

    private var fields: Array<Params>
    private var invalidFields: HashMap<Params, InvalidInputType>? = null
    private var clearInput: Boolean? = true // null -> clear only length_weight, false - don't clear

    init {
        fields = getParamsArray(form, byLength)
    }

    fun setInvalidFields(_invalidFields: HashMap<Params, InvalidInputType>) {
        invalidFields = _invalidFields
        clearInput = false
        notifyDataSetChanged()
    }

    fun setFormSelected(form: Form) {
        this.form = form
        invalidFields?.clear()
        clearInput = true
        fields = getParamsArray(form, byLength)
        notifyDataSetChanged()
    }

    fun setScenario(byLength: Boolean) {
        this.byLength = byLength
        clearInput = null
        fields = getParamsArray(form, byLength)
        notifyDataSetChanged()
    }


    inner class Holder(private val binding: HolderInputBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun TextInputLayout.showError(type: InvalidInputType) {
            isErrorEnabled = true
            error = resources.getString(type.textRes)
        }

        private fun TextInputLayout.dismissError() {
            isErrorEnabled = false
        }

        private fun TextInputEditText.clearOnlyFirstField(param: Params) {
            if (param == Params.LENGTH || param == Params.WEIGHT)
                text?.clear()
        }

        fun bind(param: Params) {

            binding.layInputField.apply {
                hint = context.getString(param.nameRes)
                invalidFields?.run {
                    get(param)?.let {
                        showError(it)
                    } ?: dismissError()
                } ?: dismissError()
            }



            binding.etInputField.apply {
                clearInput?.let { if (it) text?.clear() } ?: clearOnlyFirstField(param)
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(input: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        input: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                        val curParam = fields[adapterPosition]
                        val value = input?.let {
                            if (it.toString() != "") it.toString().toDouble() else null
                        }
                        onInput(curParam, value)
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

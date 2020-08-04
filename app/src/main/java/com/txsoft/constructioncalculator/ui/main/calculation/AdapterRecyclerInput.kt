package com.txsoft.constructioncalculator.ui.main.calculation

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.databinding.HolderInputBinding
import com.txsoft.constructioncalculator.models.createMapForValues
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.InvalidInputType
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.enums.Params.*
import com.txsoft.constructioncalculator.models.getParamsArray


class AdapterRecyclerInput(
    private val onInput: (HashMap<Params, Double?>) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerInput.Holder>() {

    private var form: Form = Form.BEAM
    private var byLength: Boolean = true
    private var fields: Array<Params>? = null
    private var invalidFields: HashMap<Params, InvalidInputType>? = null
    private var map: HashMap<Params, Double?>? = null

    fun setInvalidFields(_invalidFields: HashMap<Params, InvalidInputType>) {
        invalidFields = _invalidFields
        notifyDataSetChanged()
    }

    fun setFormSelected(form: Form) {
        this.form = form
        fields = getParamsArray(form, byLength)
        map = createMapForValues(fields)
        notifyDataSetChanged()
    }

    fun setScenario(byLength: Boolean) {
        this.byLength = byLength
        fields = getParamsArray(form, byLength)
        map = map?.apply {
            if (byLength) {
                if (containsKey(WEIGHT)) {
                    remove(WEIGHT)
                    put(LENGTH, null)
                }
            } else {
                if (containsKey(LENGTH)) {
                    remove(LENGTH)
                    put(WEIGHT, null)
                }
            }
        }
        notifyDataSetChanged()
    }


    inner class Holder(private val binding: HolderInputBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(param: Params) {


            binding.layInputField.apply {
                hint = context.getString(param.nameRes)
                invalidFields?.run {
                    get(param)?.run {
                        isErrorEnabled = true
                        error = resources.getString(textRes)
                    }
                }
            }

            binding.etInputField.apply {
                val initText = map?.run { get(param)?.toString() ?: "" } ?: ""
                setText(initText)
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        input: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                        fields?.run {
                            val curParam = get(adapterPosition)
                            val value = input?.let {
                                if (it.toString() != "") it.toString().toDouble() else null
                            }
                            map?.run {
                                put(curParam, value)
                                onInput(this)
                            }
                        }
                    }
                })
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = HolderInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = fields?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        fields?.run {
            holder.bind(get(position))
        }
    }


}

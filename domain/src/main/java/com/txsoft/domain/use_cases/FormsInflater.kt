package com.txsoft.domain.use_cases

import android.content.Context
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.models.IForm
import com.txsoft.domain.models.form.Form
import com.txsoft.domain.transformation.UIAdapterForm

class FormsInflater(context: Context) : OnResourceInflater<IForm> {

    private val transform = UIAdapterForm(context)
    private val forms: List<IForm> by lazy {
        Form.values().map { transform.fromDTO(it) }
    }
    private val namesSet: Set<String> by lazy {
        val result = hashSetOf<String>()
        forms.forEach { result.add(it.name) }
        result
    }

    override fun getResourceList(): List<IForm> {
        return forms
    }

    override fun getTypedResourceMap(): Map<String, List<IForm>> {
        val result = hashMapOf<String, List<IForm>>()
        namesSet.forEach { type -> result[type] = forms.filter { it.type == type } }
        return result
    }

    override fun getTypeSet(): Set<String> {
        return namesSet
    }
}
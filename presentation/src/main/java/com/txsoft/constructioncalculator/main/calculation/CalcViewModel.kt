package com.txsoft.constructioncalculator.main.calculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.Material

class CalcViewModel : ViewModel() {

    //is ready for calculation
    private val readyToCalculate = MutableLiveData<Boolean>()
    fun isReadyForCalculation(): LiveData<Boolean> = readyToCalculate
    fun setReadyForCalculation(ready: Boolean) {
        readyToCalculate.value = ready
    }

    //calculate by length or weight
    private val scenario = MutableLiveData<Boolean>()
    fun getScenario(): LiveData<Boolean> = scenario
    fun setScenario(byLength: Boolean) {
        scenario.value = byLength
    }

    //form of the model
    private val form = MutableLiveData<Form>()
    fun getForm(): LiveData<Form> = form
    fun setForm(_form: Form) {
        form.value = _form
    }

    //material of the model
    private val material = MutableLiveData<Material>()
    fun getMaterial(): LiveData<Material> = material
    fun setMaterial(_material: Material) {
        material.value = _material
    }
}
package com.txsoft.constructioncalculator.ui.main.calculation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.txsoft.constructioncalculator.models.enums.Form

class CalcViewModel : ViewModel() {

    //is ready for calculation
    private val readyToCalculate = MutableLiveData<Boolean>()
    fun isReadyForCalculation(): LiveData<Boolean> = readyToCalculate
    fun setReadyForCalculation(ready: Boolean) {
        readyToCalculate.value = ready
    }

    //calculate by length or weight
    private val scenario = MutableLiveData<Boolean>().apply { value = true }
    fun getScenario(): LiveData<Boolean> = scenario
    fun setScenario(byLength: Boolean) {
        scenario.value = byLength
    }

    //form of the model
    private val form = MutableLiveData<Form?>()
    fun getForm(): LiveData<Form?> = form
    fun setForm(_form: Form?) {
        form.value = _form
    }
}
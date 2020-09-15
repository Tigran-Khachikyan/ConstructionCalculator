package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IForm

interface GetForms {
    fun getAllForms(): List<IForm>
}
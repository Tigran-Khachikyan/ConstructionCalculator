package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnCallSavedModels {
    fun getSavedModels(): List<IModel>?
}
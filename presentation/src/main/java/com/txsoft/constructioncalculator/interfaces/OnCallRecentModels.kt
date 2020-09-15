package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnCallRecentModels {
    fun getRecentModels(): List<IModel>?
}
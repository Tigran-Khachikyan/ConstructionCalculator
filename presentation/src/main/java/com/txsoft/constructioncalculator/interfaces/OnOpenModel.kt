package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnOpenModel {
    fun openModel(id: Int): IModel?
}
package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnCalculate {
   suspend fun insertAsRecent(model: IModel)
}
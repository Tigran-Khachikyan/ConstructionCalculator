package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnSaveModel {
   suspend fun save(model: IModel)
}
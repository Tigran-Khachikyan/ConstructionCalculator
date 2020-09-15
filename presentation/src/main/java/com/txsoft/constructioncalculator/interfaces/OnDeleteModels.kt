package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IModel

interface OnDeleteModels {

    suspend fun deleteModelsByIds(ids: List<Int>)

    suspend fun deleteModels(models: List<IModel>)

    suspend fun deleteById(id: Int)

    suspend fun delete(model: IModel)

    suspend fun deleteAll()

}
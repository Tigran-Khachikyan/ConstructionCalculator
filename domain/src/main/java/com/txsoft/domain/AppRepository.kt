package com.txsoft.domain

import com.txsoft.data.entities.ModelEntity

interface AppRepository {

    suspend fun insert(model: ModelEntity)

    suspend fun delete(models: List<ModelEntity>)

    suspend fun delete(model: ModelEntity)

    suspend fun deleteById(id: Int)

    suspend fun deleteAll()

    fun getModels(): List<ModelEntity>?

    fun getModelById(id: Int): ModelEntity?
}
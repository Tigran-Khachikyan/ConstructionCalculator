package com.txsoft.data

import com.txsoft.data.db.Database
import com.txsoft.data.db.ModelDao
import com.txsoft.data.entities.ModelEntity
import com.txsoft.domain.AppRepository

@Suppress("UNCHECKED_CAST")
class Repository(
    db: Database
) : AppRepository {

    private val modelDao: ModelDao = db.getModelDao()

    override suspend fun insert(model: ModelEntity) {
        modelDao.insert(model)
    }

    override suspend fun delete(models: List<ModelEntity>) {
        modelDao.deleteList(models)
    }

    override suspend fun delete(model: ModelEntity) {
        modelDao.delete(model)
    }

    override suspend fun deleteById(id: Int) {
        modelDao.deleteById(id)
    }

    override suspend fun deleteAll() {
        modelDao.clearAll()
    }

    override fun getModels(): List<ModelEntity>? {
        return modelDao.getAllModels()
    }

    override fun getModelById(id: Int): ModelEntity? {
        return modelDao.get(id)
    }
}
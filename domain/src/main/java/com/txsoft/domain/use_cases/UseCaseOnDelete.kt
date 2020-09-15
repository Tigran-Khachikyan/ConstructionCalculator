package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnDeleteModels
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnDelete(private val repo: AppRepository) : OnDeleteModels {

    override suspend fun deleteModelsByIds(ids: List<Int>) {
        ids.forEach { id -> repo.deleteById(id) }
    }

    override suspend fun deleteModels(models: List<IModel>) {
        val entities = models.map { m -> ModelTransform().fromDTO(m) }
        repo.delete(entities)
    }

    override suspend fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    override suspend fun delete(model: IModel) {
        val entity = ModelTransform().fromDTO(model)
        repo.delete(entity)
    }

    override suspend fun deleteAll() {
        repo.deleteAll()
    }
}
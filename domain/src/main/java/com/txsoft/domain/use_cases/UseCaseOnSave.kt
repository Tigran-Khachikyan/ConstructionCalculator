package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnSaveModel
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnSave(private val repo: AppRepository) : OnSaveModel {

    override suspend fun save(model: IModel) {
        model.saved = true
        val entity = ModelTransform().fromDTO(model)
        repo.insert(entity)
    }
}
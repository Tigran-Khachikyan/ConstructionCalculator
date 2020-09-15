package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnCallSavedModels
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnCallSavedList(private val repo: AppRepository) : OnCallSavedModels {

    override fun getSavedModels(): List<IModel>? {
        return repo.getModels()?.map { modelEntity ->
            ModelTransform().fromEntity(modelEntity)
        }?.filter { model -> model.saved }
    }
}
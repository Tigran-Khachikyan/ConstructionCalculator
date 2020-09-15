package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnCallRecentModels
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnCallRecentList(private val repo: AppRepository) : OnCallRecentModels {

    override fun getRecentModels(): List<IModel>? {
        return repo.getModels()?.map { modelEntity ->
            ModelTransform().fromEntity(modelEntity)
        }?.takeLast(5)
    }
}
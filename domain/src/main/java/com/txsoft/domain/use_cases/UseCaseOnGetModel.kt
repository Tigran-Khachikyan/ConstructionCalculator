package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnOpenModel
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnGetModel(private val repo: AppRepository) : OnOpenModel {

    override fun openModel(id: Int): IModel? {
        val entity = repo.getModelById(id)
        return entity?.let { ModelTransform().fromEntity(it) }
    }
}
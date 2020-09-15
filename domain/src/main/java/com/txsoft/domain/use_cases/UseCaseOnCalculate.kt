package com.txsoft.domain.use_cases

import com.txsoft.constructioncalculator.interfaces.OnCalculate
import com.txsoft.constructioncalculator.models.IModel
import com.txsoft.domain.AppRepository

class UseCaseOnCalculate(private val repo: AppRepository) : OnCalculate {

    override suspend fun insertAsRecent(model: IModel) {
        model.saved = false
        val entity = ModelTransform().fromDTO(model)
        repo.insert(entity)
    }
}
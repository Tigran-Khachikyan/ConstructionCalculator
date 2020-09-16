package com.txsoft.domain.transformation

import com.txsoft.data.entities.FormEntity
import com.txsoft.domain.AdapterData
import com.txsoft.domain.models.form.Form

class DataAdapterForm : AdapterData<Form, FormEntity> {

    override fun fromDTO(dto: Form): FormEntity {
        return FormEntity(dto.nameRes, dto.imageRes, dto.markedImageRes, dto.type.name)
    }

    override fun fromEntity(entity: FormEntity): Form {
        return Form.values()
            .find { f -> f.nameRes == entity.nameRes && f.type.name.equals(entity.type) }
            ?: Form.DEFAULT
    }
}
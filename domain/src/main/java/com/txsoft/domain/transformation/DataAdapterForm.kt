package com.txsoft.domain.transformation

import com.txsoft.data.entities.FormEntity
import com.txsoft.domain.AdapterData
import com.txsoft.domain.models.form.Form

class DataAdapterForm : AdapterData<Form, FormEntity> {

    override fun fromDTO(enum: Form): FormEntity {
        return FormEntity(enum.nameRes, enum.imageRes, enum.markedImageRes, enum.type.name)
    }

    override fun fromEntity(entity: FormEntity): Form {
        return Form.values()
            .find { f -> f.nameRes == entity.nameRes && f.type.name.equals(entity.type) }
            ?: Form.DEFAULT
    }
}
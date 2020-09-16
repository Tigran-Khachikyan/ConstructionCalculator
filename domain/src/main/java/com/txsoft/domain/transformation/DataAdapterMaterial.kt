package com.txsoft.domain.transformation

import com.txsoft.data.entities.MaterialEntity
import com.txsoft.domain.AdapterData
import com.txsoft.domain.models.material.Material

class DataAdapterMaterial :
    AdapterData<Material, MaterialEntity> {

    override fun fromDTO(dto: Material): MaterialEntity {
        return MaterialEntity(dto.nameRes, dto.density, dto.colorRes, dto.type.name)
    }

    override fun fromEntity(entity: MaterialEntity): Material {
        return Material.values()
            .find { enum -> enum.nameRes == entity.nameRes && enum.type.name.equals(entity.type) }
            ?: Material.DEFAULT
    }
}
package com.txsoft.domain

interface AdapterData<DTO, ENTITY> {
    fun fromDTO(dto: DTO): ENTITY
    fun fromEntity(entity: ENTITY): DTO
}
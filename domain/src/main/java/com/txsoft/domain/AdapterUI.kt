package com.txsoft.domain

interface AdapterUI<DTO, UI> {

    fun fromDTO(dto: DTO): UI
    fun fromUi(ui: UI): DTO
}
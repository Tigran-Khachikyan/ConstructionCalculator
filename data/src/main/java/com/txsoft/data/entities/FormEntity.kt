package com.txsoft.data.entities

import androidx.room.Entity

@Entity(tableName = "FORM")
data class FormEntity(
    val nameRes: Int,
    val imageRes: Int,
    val markedImageRes: Int,
    val type: String
)
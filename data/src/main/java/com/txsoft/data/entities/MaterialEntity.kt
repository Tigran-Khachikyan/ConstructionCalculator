package com.txsoft.data.entities

import androidx.room.Entity

@Entity(tableName = "MATERIAL")
data class MaterialEntity (
    val nameRes: Int,
    val density: Double,
    val colorRes: Int,
    val type: String
)
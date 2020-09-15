package com.txsoft.data.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "SHAPE")
data class ShapeEntity(
    @Embedded(prefix = "form_")
    val form: FormEntity,
    val length: Double,
    val width: Double,
    val height: Double,
    val thickness: Double,
    val thickness1: Double,
    val thickness2: Double,
    val diameter: Double,
    val side: Double,
    val area: Double,
    val volume: Double
/*  @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0*/
)
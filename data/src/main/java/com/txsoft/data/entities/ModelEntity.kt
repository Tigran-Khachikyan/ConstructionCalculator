package com.txsoft.data.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "MODEL")
data class ModelEntity(
    @Embedded(prefix = "shape_")
    val shape: ShapeEntity,
    @Embedded(prefix = "material_")
    val material: MaterialEntity,
    val weight: Double,
    val name: String,
    val dateOfCreation: String,
    val saved: Boolean,
    val scenarioByLength: Boolean
/*    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0*/
)


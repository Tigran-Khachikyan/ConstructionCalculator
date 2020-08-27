package com.txsoft.constructioncalculator.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.txsoft.constructioncalculator.models.enums.Material
import java.util.*

class Model private constructor(
    var shape: Shape,
    val material: Material,
    var weight: Double,
    val name: String? = null,
    private var unit: Unit
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private val id: Int = 0
    val dateOfCreation: String = Calendar.getInstance().time.toString().split(' ')[0]

    companion object {
        fun createByLength(
            shape: Shape,
            material: Material,
            name: String? = null,
            unit: Unit = Unit.METRIC
        ): Model? {

            val weight = when (unit) {
                Unit.METRIC -> shape.volume?.let { material.density * it / 1000 }
                Unit.IMPERIAL -> shape.volume?.let { fromGCm3ToLbIn3(material.density) * it }
            }
            return weight?.let { Model(shape, material, it, name, unit) }
        }

        fun createByWeight(
            shape: Shape,
            material: Material,
            weight: Double,
            name: String? = null,
            unit: Unit = Unit.METRIC
        ): Model? {

            shape.length = when (unit) {
                Unit.METRIC -> 1000 * weight / (shape.area * material.density)
                Unit.IMPERIAL -> weight / (shape.area * fromGCm3ToLbIn3(material.density))
            }
            return shape.length?.let { Model(shape, material, weight, name, unit) }
        }
    }
}


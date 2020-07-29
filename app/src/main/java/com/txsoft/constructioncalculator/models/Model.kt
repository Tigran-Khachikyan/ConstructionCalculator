package com.txsoft.constructioncalculator.models

import android.content.Context
import androidx.room.*
import java.util.*

class Model constructor(
    val shape: Shape,
    val material: Material,
    val units: Unit,
    var weight: Double,
    var dateOfCreation: String,
    var name: String? = null
) {
    var id: Int = 0

/*    companion object {
        fun createByLength(shape: Shape, material: Material, units: Unit): Model? {

            val weight = if (units == Unit.METRIC)
                shape.run { volume?.let { material.density * it / 1000 } }
            else
                shape.run { volume?.let { fromGCm3ToLbIn3(material.density) * it } }
            val date = Calendar.getInstance().time.toString().split(' ')[0]
            return weight?.let { Model(shape, material, units, it, true, date) }
                ?.apply { setPrice() }
        }

        fun createByWeight(shape: Shape, material: Material, units: Unit, weight: Double): Model? {

            shape.length = if (units == Unit.METRIC)
                shape.area?.let { 1000 * weight / (it * material.density) }
            else
                shape.area?.let { weight / (it * fromGCm3ToLbIn3(material.density)) }
            val date = Calendar.getInstance().time.toString().split(' ')[0]
            return shape.length?.let { Model(shape, material, units, weight, false, date) }
                ?.apply { setPrice() }
        }
    }

    fun setPrice() = run { price = material.price?.let { Price(it.base, (it.value * weight)) } }

    fun getResultToSend(context: Context): String {
        return this.run {
            val nameText = name?.let { "Model name: $it" } ?: ""
            val shapeText = shape.let {
                "${(R.string.shape).name(context)}: ${it.form.nameRes.name(context)}"
            }
            val matText = material.let {
                "${(R.string.material).name(context)}: ${it.substance.nameRes.name(context)}"
            }
            nameText + "\n" + shapeText + "\n" + matText
        }
    }*/
}


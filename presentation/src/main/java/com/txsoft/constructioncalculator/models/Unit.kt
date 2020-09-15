package com.txsoft.constructioncalculator.models

import androidx.room.TypeConverter
import com.txsoft.constructioncalculator.R

enum class Unit(
    val nameRes: Int,
    val distance: String,
    val weight: String,
    val density: String,
    val volume: String
) {

    METRIC(R.string.metric, "cm", "kg", "g/cm3", "cm3"),
    IMPERIAL(R.string.imperial, "inch", "pound", "lb/in3", "in3");
}

class UnitTypeConverter {

    @TypeConverter
    fun getUnitByNameRes(res: Int): Unit? = Unit.values().find { u -> u.nameRes == res }

    @TypeConverter
    fun getUnitName(unit: Unit): Int = unit.nameRes

}
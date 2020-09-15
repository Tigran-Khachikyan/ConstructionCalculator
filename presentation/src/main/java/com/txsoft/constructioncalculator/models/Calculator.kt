package com.txsoft.constructioncalculator.models

import com.txsoft.constructioncalculator.models.enums.Form
import com.txsoft.constructioncalculator.models.enums.Form.*
import com.txsoft.constructioncalculator.models.enums.Params
import com.txsoft.constructioncalculator.models.enums.Params.*


fun getParamsArray(form: IForm): Array<Params> {

    return when (form) {
        ANGLE -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS, COUNT)
        ROUND_BAR -> arrayOf(LENGTH, WEIGHT, DIAMETER, COUNT)
        SQUARE_BAR -> arrayOf(LENGTH, WEIGHT, SIDE, COUNT)
        SQUARE_TUBE -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS, COUNT)
        BEAM -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS_1, THICKNESS_2, COUNT)
        CHANNEL -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS, COUNT)
        FLAT_BAR -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, COUNT)
        HEXAGONAL_BAR -> arrayOf(LENGTH, WEIGHT, HEIGHT, COUNT)
        HEXAGONAL_TUBE -> arrayOf(LENGTH, WEIGHT, DIAMETER, SIDE, COUNT)
        HEXAGONAL_HEX -> arrayOf(LENGTH, WEIGHT, WIDTH, THICKNESS, COUNT)
        PIPE -> arrayOf(LENGTH, WEIGHT, DIAMETER, THICKNESS, COUNT)
        T_BAR -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS, COUNT)


        else -> arrayOf(LENGTH, WEIGHT, WIDTH, HEIGHT, THICKNESS, COUNT)
    }
}

fun getParamsArray(form: IForm, byLength: Boolean): List<Params> {

    val exceptionParam = if (byLength) WEIGHT else LENGTH
    val array = getParamsArray(form)
    return array.filter { param -> param != exceptionParam }
}

fun getParamsValuesMap(form: IForm, byLength: Boolean): HashMap<Params, Double?> {

    val result = HashMap<Params, Double?>()
    val array = getParamsArray(form, byLength)
    array.forEach { param -> result[param] = null }
    return result
}
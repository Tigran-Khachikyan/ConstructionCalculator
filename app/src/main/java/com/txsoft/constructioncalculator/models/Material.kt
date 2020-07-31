package com.txsoft.constructioncalculator.models

import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.MaterialType.*

enum class Material(val nameRes: Int, val density: Double, val type: MaterialType) {

    ALUMINIUM(R.string.aluminium, 2.7, METAL),
    SILVER(R.string.silver, 19.32, METAL),
    BRASS(R.string.brass, 19.32, METAL),
    TIN(R.string.tin, 7.3, METAL),
    BRONZE(R.string.bronze, 19.32, METAL),
    CAST_IRON(R.string.cast_iron, 19.32, METAL),
    PLATINUM(R.string.platinum, 19.32, METAL),
    CHROME(R.string.chrome, 19.32, METAL),
    GOLD(R.string.gold, 19.32, METAL);

    val unit = Unit.METRIC.density
}
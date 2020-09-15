package com.txsoft.domain.models.material

import com.txsoft.domain.R
import com.txsoft.domain.models.material.MaterialType.*
import com.txsoft.domain.models.units.Units


enum class Material(
    val nameRes: Int,
    val density: Double,
    val colorRes: Int,
    val type: MaterialType
) {

    ALUMINIUM(R.string.aluminium, 2.7, R.color.aluminium, METAL),
    SILVER(R.string.silver, 19.32, R.color.aluminium, METAL),
    BRASS(R.string.brass, 19.32, R.color.aluminium, METAL),
    TIN(R.string.tin, 7.3, R.color.aluminium, METAL),
    BRONZE(R.string.bronze, 19.32, R.color.aluminium, METAL),
    CAST_IRON(R.string.cast_iron, 19.32, R.color.aluminium, METAL),
    PLATINUM(R.string.platinum, 19.32, R.color.aluminium, METAL),
    CHROME(R.string.chrome, 19.32, R.color.aluminium, METAL),
    GOLD(R.string.gold, 19.32, R.color.aluminium, METAL),

    DEFAULT(R.string.gold, 19.32, R.color.aluminium, METAL);

    val unit = Units.METRIC.density
}
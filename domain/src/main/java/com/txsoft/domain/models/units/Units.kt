package com.txsoft.domain.models.units

import com.txsoft.domain.R

enum class Units(
    val nameRes: Int,
    val distance: String,
    val weight: String,
    val density: String,
    val volume: String
) {

    METRIC(R.string.metric, "cm", "kg", "g/cm3", "cm3"),
    IMPERIAL(R.string.imperial, "inch", "pound", "lb/in3", "in3");
}

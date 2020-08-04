package com.txsoft.constructioncalculator.models.enums

import com.txsoft.constructioncalculator.R

enum class InvalidInputType(val textRes: Int) {

    NULL(R.string.invalid_null),
    ZERO(R.string.invalid_0),
    OTHER(R.string.invalid_other)
}
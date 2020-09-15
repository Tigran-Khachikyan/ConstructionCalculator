package com.txsoft.constructioncalculator.models

import android.graphics.drawable.Drawable

interface IForm {
    val name: String
    val image: Drawable
    val markedImageRes: Drawable
    val type: String
}
package com.txsoft.constructioncalculator.models

import android.graphics.drawable.Drawable

interface IForm : IResource {
    val image: Drawable
    val markedImageRes: Drawable
}
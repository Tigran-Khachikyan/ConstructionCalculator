package com.txsoft.domain.transformation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.txsoft.constructioncalculator.models.IForm
import com.txsoft.domain.AdapterUI
import com.txsoft.domain.models.form.Form

class UIAdapterForm(private val context: Context) : AdapterUI<Form, IForm> {

    override fun fromDTO(enum: Form): IForm {
        return object : IForm {
            override val name: String
                get() = context.resources.getString(enum.nameRes)
            override val image: Drawable
                get() = ContextCompat.getDrawable(context, enum.imageRes)!!
            override val markedImageRes: Drawable
                get() = ContextCompat.getDrawable(context, enum.markedImageRes)!!
            override val type: String
                get() = context.resources.getString(enum.type.nameRes)
        }
    }


    override fun fromUi(model: IForm): Form {
        return Form.values().find { enum ->
            context.resources.getString(enum.nameRes) == model.name &&
                    context.resources.getString(enum.type.nameRes) == model.type
        } ?: Form.DEFAULT
    }
}
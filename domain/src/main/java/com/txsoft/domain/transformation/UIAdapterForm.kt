package com.txsoft.domain.transformation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.txsoft.constructioncalculator.models.IForm
import com.txsoft.domain.AdapterUI
import com.txsoft.domain.models.form.Form

class UIAdapterForm(private val context: Context) : AdapterUI<Form, IForm> {

    override fun fromDTO(dto: Form): IForm {
        return object : IForm {
            override val name: String
                get() = context.resources.getString(dto.nameRes)
            override val image: Drawable
                get() = ContextCompat.getDrawable(context, dto.imageRes)!!
            override val markedImageRes: Drawable
                get() = ContextCompat.getDrawable(context, dto.markedImageRes)!!
            override val type: String
                get() = context.resources.getString(dto.type.nameRes)
        }
    }


    override fun fromUi(ui: IForm): Form {
        return Form.values().find { enum ->
            context.resources.getString(enum.nameRes) == ui.name &&
                    context.resources.getString(enum.type.nameRes) == ui.type
        } ?: Form.DEFAULT
    }
}
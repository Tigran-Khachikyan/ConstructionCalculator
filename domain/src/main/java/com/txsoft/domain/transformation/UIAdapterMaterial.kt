package com.txsoft.domain.transformation

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.txsoft.constructioncalculator.models.IMaterial
import com.txsoft.domain.AdapterData
import com.txsoft.domain.AdapterUI
import com.txsoft.domain.models.material.Material

class UIAdapterMaterial(private val context: Context) : AdapterUI<Material, IMaterial> {

    override fun fromDTO(enum: Material): IMaterial {
        return object : IMaterial {
            override val name: String
                get() = context.getString(enum.nameRes)
            override val density: Double
                get() = enum.density
            override val type: String
                get() = context.getString(enum.type.nameRes)
            override val color: Int
                get() = ContextCompat.getColor(context, enum.colorRes)
        }
    }

    override fun fromUi(ui: IMaterial): Material {
        return Material.values().find { enum ->
            context.getString(enum.type.nameRes) == ui.name &&
                    context.getString(enum.type.nameRes) == ui.type
        } ?: Material.DEFAULT
    }
}
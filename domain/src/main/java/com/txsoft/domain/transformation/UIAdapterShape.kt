package com.txsoft.domain.transformation

import android.content.Context
import com.txsoft.constructioncalculator.models.IShape
import com.txsoft.domain.AdapterUI
import com.txsoft.domain.R
import com.txsoft.domain.models.Shape
import com.txsoft.domain.models.form.Form.*

class UIAdapterShape(private val context: Context) : AdapterUI<Shape, IShape> {

    override fun fromDTO(shape: Shape): IShape {
        return when(shape.form){
            
        }
    }

    override fun fromUi(iShape: IShape): Shape {
        val form = values().find { f -> context.getString(f.nameRes) == iShape.form.name }
            ?: DEFAULT
        return when (form) {
            ROUND_BAR -> iShape.run {
                if (length != null && diameter != null)
                    Shape.RoundBar(length!!, diameter!!)
                else Shape.Default()
            }
            ANGLE -> iShape.run {
                if (length != null && width != null && height != null && thickness != null)
                    Shape.Angle(length!!, width!!, height!!, thickness!!)
                else Shape.Default()
            }
            BEAM -> iShape.run {
                if (length != null && width != null && height != null && thickness1 != null && thickness2 != null)
                    Shape.Beam(length!!, width!!, height!!, thickness1!!, thickness2!!)
                else Shape.Default()
            }
            CHANNEL -> iShape.run {
                if (length != null && width != null && height != null && thickness != null)
                    Shape.Channel(length!!, width!!, height!!, thickness!!)
                else Shape.Default()
            }
            FLAT_BAR -> iShape.run {
                if (length != null && width != null && height != null)
                    Shape.FlatBar(length!!, width!!, height!!)
                else Shape.Default()
            }
            HEXAGONAL_BAR -> iShape.run {
                if (length != null && height != null)
                    Shape.HexagonalBar(length!!, height!!)
                else Shape.Default()
            }
            HEXAGONAL_TUBE -> iShape.run {
                if (length != null && side != null && diameter != null)
                    Shape.HexagonalTube(length!!, side!!, diameter!!)
                else Shape.Default()
            }
            HEXAGONAL_HEX -> iShape.run {
                if (length != null && width != null && thickness != null)
                    Shape.HexagonalHex(length!!, width!!, thickness!!)
                else Shape.Default()
            }
            PIPE -> iShape.run {
                if (length != null && thickness != null && diameter != null)
                    Shape.Pipe(length!!,thickness!!, diameter!!)
                else Shape.Default()
            }
            else -> Shape.Default()
        }
    }
}
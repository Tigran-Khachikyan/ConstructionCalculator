package com.txsoft.constructioncalculator.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.txsoft.constructioncalculator.models.enums.Form

sealed class Shape constructor(
    val form: Form,
    var length: Double?
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shape_id")
    private var id: Int = 0
    abstract val area: Double
    val volume: Double?
        get() = length?.let { it * area }

    class Angle(
        length: Double?,
        val width: Double,
        val height: Double,
        val thickness: Double
    ) : Shape(Form.ANGLE, length) {
        override val area: Double
            get() = width * height - (width - thickness) * (height - thickness)
    }

    class Beam(
        length: Double?,
        val width: Double,
        val height: Double,
        val thickness1: Double,
        val thickness2: Double
    ) : Shape(Form.BEAM, length) {
        override val area: Double
            get() = 2 * width * thickness2 + (height - 2 * thickness2) * thickness1
    }

    class Channel(
        length: Double?,
        val width: Double,
        val height: Double,
        val thickness: Double
    ) : Shape(Form.CHANNEL, length) {
        override val area: Double
            get() = (width * height) - (width - 2 * thickness) * (height - thickness)
    }

    class FlatBar(
        length: Double?,
        val width: Double,
        val height: Double
    ) : Shape(Form.FLAT_BAR, length) {
        override val area: Double
            get() = width * height
    }

    class HexagonalBar(
        length: Double?,
        val height: Double
    ) : Shape(Form.HEXAGONAL_BAR, length) {
        override val area: Double
            get() = 6 * (height / 2) * (height / 2) / kotlin.math.sqrt(3.0)
    }

    class HexagonalHex(
        length: Double?,
        val width: Double,
        val thickness: Double
    ) : Shape(Form.HEXAGONAL_HEX, length) {
        override val area: Double
            get() = (width / 2) * (width / 2) / kotlin.math.sqrt(3.0) - (((width - 2 * thickness) / 2)
                    * ((width - 2 * thickness) / 2) / kotlin.math.sqrt(3.0))
    }

    class HexagonalTube(
        length: Double?,
        val side: Double,
        val diameter: Double
    ) : Shape(Form.HEXAGONAL_TUBE, length) {
        override val area: Double
            get() = 3 * kotlin.math.sqrt(3.0) / 2 * side * side - (kotlin.math.PI * (diameter / 2) * (diameter / 2))
    }

    class Pipe(
        length: Double?,
        val thickness: Double,
        val diameter: Double
    ) : Shape(Form.PIPE, length) {
        override val area: Double
            get() = (kotlin.math.PI * (diameter / 2) * (diameter / 2)) -
                    (kotlin.math.PI * ((diameter - thickness) / 2) * ((diameter - thickness) / 2))
    }

    class RoundBar(
        length: Double?,
        val diameter: Double
    ) : Shape(Form.ROUND_BAR, length) {
        override val area: Double
            get() = kotlin.math.PI * (diameter / 2) * (diameter / 2)
    }

    class SquareBar(
        length: Double?,
        val side: Double
    ) : Shape(Form.SQUARE_BAR, length) {
        override val area: Double
            get() = side * side
    }

    class SquareTube(
        length: Double?,
        val width: Double,
        val height: Double,
        val thickness: Double
    ) : Shape(Form.SQUARE_TUBE, length) {
        override val area: Double
            get() = height * width - (height - 2 * thickness) * (width - 2 * thickness)
    }

    class TBar(
        length: Double?,
        val width: Double,
        val height: Double,
        val thickness: Double
    ) : Shape(Form.T_BAR, length) {
        override val area: Double
            get() = width * thickness + (height - thickness) * thickness
    }

}
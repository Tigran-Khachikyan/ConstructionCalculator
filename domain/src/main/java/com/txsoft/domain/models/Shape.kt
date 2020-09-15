package com.txsoft.domain.models

import com.txsoft.domain.models.form.Form

sealed class Shape(var form: Form) {

    abstract val area: Double
    abstract val volume: Double

    class Angle(
        val length: Double,
        val width: Double,
        val height: Double,
        val thickness: Double,
        override var area: Double = width * height - (width - thickness) * (height - thickness),
        override val volume: Double = length * area
    ) : Shape(Form.ANGLE)

    class Beam(
        val length: Double,
        val width: Double,
        val height: Double,
        val thickness1: Double,
        val thickness2: Double,
        override var area: Double = 2 * width * thickness2 + (height - 2 * thickness2) * thickness1,
        override val volume: Double = length * area
    ) : Shape(Form.BEAM)

    class Channel(
        val length: Double,
        val width: Double,
        val height: Double,
        val thickness: Double,
        override var area: Double = (width * height) - (width - 2 * thickness) * (height - thickness),
        override val volume: Double = length * area
    ) : Shape(Form.CHANNEL)

    class FlatBar(
        val length: Double,
        val width: Double,
        val height: Double,
        override var area: Double = width * height,
        override val volume: Double = length * area
    ) : Shape(Form.FLAT_BAR)

    class HexagonalBar(
        val length: Double,
        val height: Double,
        override var area: Double = 6 * (height / 2) * (height / 2) / kotlin.math.sqrt(3.0),
        override val volume: Double = length * area
    ) : Shape(Form.HEXAGONAL_BAR)

    class HexagonalHex(
        val length: Double,
        val width: Double,
        val thickness: Double,
        override var area: Double = (width / 2) * (width / 2) / kotlin.math.sqrt(3.0) - (((width - 2 * thickness) / 2)
                * ((width - 2 * thickness) / 2) / kotlin.math.sqrt(3.0)),
        override val volume: Double = length * area
    ) : Shape(Form.HEXAGONAL_HEX)


    class HexagonalTube(
        val length: Double,
        val side: Double,
        val diameter: Double,
        override var area: Double = 3 * kotlin.math.sqrt(3.0) / 2 * side * side - (kotlin.math.PI * (diameter / 2) * (diameter / 2)),
        override val volume: Double = length * area
    ) : Shape(Form.HEXAGONAL_TUBE)

    class Pipe(
        val length: Double,
        val thickness: Double,
        val diameter: Double,
        override var area: Double = (kotlin.math.PI * (diameter / 2) * (diameter / 2)) -
                (kotlin.math.PI * ((diameter - thickness) / 2) * ((diameter - thickness) / 2)),
        override val volume: Double = length * area
    ) : Shape(Form.PIPE)

    class RoundBar(
        val length: Double,
        val diameter: Double,
        override var area: Double = kotlin.math.PI * (diameter / 2) * (diameter / 2),
        override val volume: Double = length * area
    ) : Shape(Form.ROUND_BAR)

    class SquareBar(
        val length: Double,
        val side: Double,
        override var area: Double = side * side,
        override val volume: Double = length * area
    ) : Shape(Form.SQUARE_BAR)

    class SquareTube(
        val length: Double,
        val width: Double,
        val height: Double,
        val thickness: Double,
        override var area: Double = height * width - (height - 2 * thickness) * (width - 2 * thickness),
        override val volume: Double = length * area

    ) : Shape(Form.SQUARE_TUBE)

    class TBar(
        val length: Double,
        val width: Double,
        val height: Double,
        val thickness: Double,
        override var area: Double = width * thickness + (height - thickness) * thickness,
        override val volume: Double = length * area

    ) : Shape(Form.T_BAR)

    class Default() : Shape(Form.DEFAULT) {
        override val area: Double
            get() = 0.0
        override val volume: Double
            get() = 0.0
    }
}

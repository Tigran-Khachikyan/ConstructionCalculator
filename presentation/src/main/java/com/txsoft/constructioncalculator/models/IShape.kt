package com.txsoft.constructioncalculator.models

interface IShape {
    val form: IForm
    val length: Double?
    val width: Double?
    val height: Double?
    val thickness: Double?
    val thickness1: Double?
    val thickness2: Double?
    val diameter: Double?
    val radius: Double?
    val side: Double?
    val frontArea: Double?
    val SurfaceArea: Double
    val TotalArea: Double
    val volume: Double
}
package com.txsoft.constructioncalculator.models

interface IModel {
    val id: Int
    val shape: IShape
    val material: IMaterial
    var weight: Double
    var name: String?
    var saved: Boolean
    val scenarioByLength: Boolean
    val dateOfCreation: String
}
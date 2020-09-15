package com.txsoft.domain.models

import com.txsoft.domain.models.material.Material

data class Model(
    val id: Int,
    val shape: Shape,
    val material: Material,
    val weight: Double,
    val name: String,
    val dateOfCreation: String,
    val saved: Boolean,
    val scenarioByLength: Boolean
)


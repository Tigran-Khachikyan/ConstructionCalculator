package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IMaterial

interface GetMaterials {
    fun getAllMaterials(): List<IMaterial>
}
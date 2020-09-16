package com.txsoft.domain.use_cases

import android.content.Context
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.models.IMaterial
import com.txsoft.domain.models.material.Material
import com.txsoft.domain.transformation.UIAdapterMaterial

class MaterialsInflater(context: Context) : OnResourceInflater<IMaterial> {

    private val transform = UIAdapterMaterial(context)
    private val materials: List<IMaterial> by lazy {
        Material.values().map { transform.fromDTO(it) }
    }
    private val namesSet: Set<String> by lazy {
        val result = hashSetOf<String>()
        materials.forEach { result.add(it.name) }
        result
    }

    override fun getResourceList(): List<IMaterial> {
        return materials
    }

    override fun getTypedResourceMap(): Map<String, List<IMaterial>> {
        val result = hashMapOf<String, List<IMaterial>>()
        namesSet.forEach { type -> result[type] = materials.filter { it.type == type } }
        return result
    }

    override fun getTypeSet(): Set<String> {
        return namesSet
    }
}
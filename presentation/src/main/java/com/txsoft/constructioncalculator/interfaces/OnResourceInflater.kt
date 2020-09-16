package com.txsoft.constructioncalculator.interfaces

import com.txsoft.constructioncalculator.models.IResource

interface OnResourceInflater<T : IResource> {
    fun getResourceList(): List<T>
    fun getTypedResourceMap(): Map<String, List<T>>
    fun getTypeSet(): Set<String>
}
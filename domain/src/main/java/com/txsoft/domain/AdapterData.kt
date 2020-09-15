package com.txsoft.domain

interface AdapterData<A, B> {
    fun fromDTO(a: A): B
    fun fromEntity(b: B): A
}
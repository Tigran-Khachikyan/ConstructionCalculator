package com.txsoft.domain

interface AdapterUI<A, B> {
    fun fromDTO(a: A): B
    fun fromUi(b: B): A
}
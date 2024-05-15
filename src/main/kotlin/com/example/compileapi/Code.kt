package com.example.compileapi

import jakarta.persistence.Entity

@Entity
class Code(
    val title: String,
    val code: String,
    val author: String,
    val language: String,
): BaseEntity() {

}
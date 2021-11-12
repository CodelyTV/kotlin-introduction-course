package com.codely.demo.cat

data class Name(val value: String) {
    companion object {
        fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty()) {
            throw InvalidName(value)
        } else {
            Name(value)
        }
    }

}

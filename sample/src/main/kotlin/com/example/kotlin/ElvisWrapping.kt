package com.example.kotlin

class ElvisWrapping {
    fun foo() {
        "".takeUnless { it.isEmpty() } ?: return
    }

    fun bar() {
        ""
            .takeUnless { it.isEmpty() }
            ?: return
    }

    fun baz() {
        "".takeUnless {
            it.isEmpty()
        } ?: return
    }
}

package com.example.kotlin

class SwitchCaseBranching(foo: Int, bar: () -> Unit) {
    init {
        when (foo) {
            0 -> println()
            1, 2 -> println()
        }
    }
}

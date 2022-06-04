package com.whynotpot.frog
import androidx.fragment.app.Fragment

interface RunApi {
    fun run(x: Int, y: Int): Int
    fun runString(s: String): String
    fun getFragment(): Fragment
}
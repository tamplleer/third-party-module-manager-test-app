package com.whynotpot.third_party_module_manager

import android.annotation.SuppressLint
import android.content.Context

object Common {
    @SuppressLint("StaticFieldLeak")
    var context: Context? = null
    const val PACKAGE_NAME = "com.whynotpot."
    const val MODULE_INFO_NAME =".ModuleInfo"
}
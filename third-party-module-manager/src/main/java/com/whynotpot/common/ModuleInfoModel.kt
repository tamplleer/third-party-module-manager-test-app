package com.whynotpot.common

abstract class ModuleInfoModel<T>(
    open val name: String = "None",
    open val description: String = "None",
    open val className: String = "None",
    open val data: T? = null
)
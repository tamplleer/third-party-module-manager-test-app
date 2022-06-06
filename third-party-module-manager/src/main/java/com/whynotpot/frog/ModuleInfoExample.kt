package com.whynotpot.frog

import com.whynotpot.common.ModuleInfoModel

data class ModuleInfoExample<T>(
    override val name: String = "None",
    override val description: String = "None",
    override val className: String = "None",
    override val data: T? = null
) : ModuleInfoModel<T>(name, description, className, data)

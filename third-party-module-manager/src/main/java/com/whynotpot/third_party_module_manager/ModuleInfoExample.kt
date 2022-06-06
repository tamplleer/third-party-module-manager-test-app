package com.whynotpot.third_party_module_manager

import com.whynotpot.common.ModuleInfoModel

data class ModuleInfoExample<T>(
    override val name: String = "None",
    override val description: String = "None",
    override val className: String = "None",
    override val data: T? = null
) : ModuleInfoModel<T>(name, description, className, data)

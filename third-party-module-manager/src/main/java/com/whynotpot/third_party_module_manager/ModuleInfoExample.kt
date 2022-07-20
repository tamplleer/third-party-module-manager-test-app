package com.whynotpot.third_party_module_manager

import com.whynotpot.common.ModuleInfoModel

data class ModuleInfoExample<T>(
    override val name: String = "None"
) : ModuleInfoModel<T>(name)

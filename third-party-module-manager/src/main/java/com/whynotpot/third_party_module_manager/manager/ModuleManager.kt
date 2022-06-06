package com.whynotpot.third_party_module_manager.manager

import com.whynotpot.common.ModuleInfoModel

interface ModuleManager<T> {
    fun load(name: String)
    fun checkExist(name: String): ModuleManagerStatus
    fun delete(name: String): ModuleManagerStatus
    fun enable(name: String)
    fun loadListModule(): List<String>
    fun loadModules(names: List<String>)
    fun moduleInfo(name: String): ModuleInfoModel<T>
}
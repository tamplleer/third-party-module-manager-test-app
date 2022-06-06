package com.whynotpot.third_party_module_manager.loader

interface ModulesLoader {
    fun installModule(moduleName: String)
    fun modulesList(): List<String>
    fun deleteModule(modulesNames: List<String>)
    fun deleteModule(moduleName: String)
}
package com.whynotpot.third_party_module_manager.manager

interface ModuleManager<T> {
    fun load(name: String)//todo список всех доступных модулей, как узнавать инфу
    fun checkExist(name: String): ModuleManagerStatus
    fun delete(name: String): ModuleManagerStatus
    fun init(name: String)
    fun enable(name: String)//todo enable to use
    fun loadListModule()
    fun loadModules(names:List<String>)

}
package com.whynotpot.third_party_module_manager.loader

enum class ModuleLoaderStatus {
    LOADING, //todo ....10%......40%..............90%...96%....99%....100%! you meowed successfully
    LOADED,
    ERROR
}

sealed class LoadStatus
object Loading : LoadStatus()
object Loaded : LoadStatus()
data class Error(val message: String) : LoadStatus()
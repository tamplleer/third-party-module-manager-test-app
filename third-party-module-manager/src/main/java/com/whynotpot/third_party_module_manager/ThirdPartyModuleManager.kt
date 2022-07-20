package com.whynotpot.third_party_module_manager

import android.content.Context
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.manager.ModuleManager
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl

object ThirdPartyModuleManager {
    fun <T> moduleManager(
        filePathType: FilePathType,
        context: Context,
    ): ModuleManager<T> {
        return ModuleManagerImpl(filePathType, context)
    }

    fun startListModuleActivity(context: Context) {
        ModulesManagerActivity.startActivityModuleManager(context)
    }
}
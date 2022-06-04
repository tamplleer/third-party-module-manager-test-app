package com.whynotpot.third_party_module_manager

import android.content.Context
import android.content.pm.ModuleInfo
import android.util.Log
import com.whynotpot.frog.RunApi
import java.io.File

class ModuleManager {
    companion object {
        fun load(context: Context, name: String) {
            val file: File? = context.getExternalFilesDir("modals")
            if (file == null) {
                Log.e("aa", "Not found")
            } else {
                val moduleLoader = ModuleLoader(context, file)
                moduleLoader.setupModulesDownload(name)
                moduleLoader.modalsList()
                moduleLoader.installModule(name)
            }

        }

        fun execute() {

        }

        fun getModuleInfo(context: Context): com.whynotpot.frog.ModuleInfoModel<RunApi>? {
            return ModuleLoader.getModuleInfo(context.classLoader)
        }
    }
}
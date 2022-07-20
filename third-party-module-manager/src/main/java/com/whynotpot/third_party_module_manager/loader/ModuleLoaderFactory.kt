package com.whynotpot.third_party_module_manager.loader

import android.content.Context
import com.whynotpot.third_party_module_manager.ViewModelManager
import java.io.File

enum class LoaderType {
    FAKE_SPLIT_LOADER
}

class ModuleLoaderFactory {
    companion object {
        fun get(type: LoaderType, context: Context, externalFileDir: File, viewModelManager: ViewModelManager?): ModulesLoader {
            return when (type) {
                LoaderType.FAKE_SPLIT_LOADER -> {
                    ModulesLoaderFakeSplitInstall.init(context, externalFileDir,viewModelManager)
                    return ModulesLoaderFakeSplitInstall
                }
                else -> {
                    ModulesLoaderFakeSplitInstall.init(context, externalFileDir,viewModelManager)
                    return ModulesLoaderFakeSplitInstall
                }
            }
        }
    }

}
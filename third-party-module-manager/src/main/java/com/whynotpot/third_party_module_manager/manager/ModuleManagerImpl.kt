package com.whynotpot.third_party_module_manager.manager

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.whynotpot.frog.ModuleInfoModel
import com.whynotpot.frog.RunApi
import com.whynotpot.third_party_module_manager.file.FileLoaderImpl
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.loader.LoaderType
import com.whynotpot.third_party_module_manager.loader.ModuleLoaderFactory
import com.whynotpot.third_party_module_manager.loader.ModulesLoaderFakeSplitInstall
import java.io.File

class ModuleManagerImpl<T>(
    private val filePathType: FilePathType,
    private val fileName: String,
    val context: Context,
) : ModuleManager<T> {

    val externalModuleFile: File? by lazy { FileLoaderImpl.get(filePathType, fileName, context) }

    override fun load(name: String) {

        if (externalModuleFile == null) {
            Log.e("aa", "Not found")
        } else {
            try {
                val modulesLoader =
                    ModuleLoaderFactory.get(
                        LoaderType.FAKE_SPLIT_LOADER,
                        context,
                        externalModuleFile!!
                    )
                modulesLoader?.modulesList()
                modulesLoader?.installModule(name)
            } catch (e: Exception) {
                Log.e("aa", e.message.toString())
            }

        }

    }


    fun getModalList(): List<String> {
        try {
            return ModulesLoaderFakeSplitInstall.modulesList()
        } catch (e: Exception) {
            Log.e("aa", e.message.toString())
        }
        return listOf()
    }

    fun getModalListWithInfo(): List<ModuleInfoModel<T>> {
        try {
            return ModulesLoaderFakeSplitInstall.modulesList().map { moduleInfo(it)!! }
        } catch (e: Exception) {
            Log.e("aa", e.message.toString())
        }
        return listOf()
    }

    fun moduleInfo(name: String): ModuleInfoModel<T>? {
        try {
            val moduleClass: Class<ModuleInfoModel<T>> =
                context.classLoader.loadClass("com.whynotpot.frog.${name}.ModuleInfo") as Class<ModuleInfoModel<T>>
            return moduleClass.newInstance()
        } catch (e: Exception) {
            Log.e("aa", e.message.toString())
        }
        return null
    }

    fun executeModule(): T {
        try {
            val classLoader = context.classLoader
            Log.i(
                "aa",
                classLoader.loadClass("com.whynotpot.frog.Run").classes.toList()
                    .toString()
            )
            val runs: Class<T> =
                classLoader.loadClass("com.whynotpot.frog.Run") as Class<T>
            return runs.newInstance()
        } catch (e: Exception) {
            Log.i("aa", "fRoG error ${e.message.toString()}")
            throw Exception(e)
        }
    }


    override fun checkExist(name: String): ModuleManagerStatus {
        TODO("Not yet implemented")
    }

    override fun delete(name: String): ModuleManagerStatus {
        TODO("Not yet implemented")
    }

    override fun init(name: String) {
        TODO("Not yet implemented")
    }

    override fun enable(name: String) {
        TODO("Not yet implemented")
    }

    override fun loadListModule() {
        TODO("Not yet implemented")
    }

    override fun loadModules(names: List<String>) {
        TODO("Not yet implemented")
    }

}
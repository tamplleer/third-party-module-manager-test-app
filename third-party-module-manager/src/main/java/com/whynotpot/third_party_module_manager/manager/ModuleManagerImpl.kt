package com.whynotpot.third_party_module_manager.manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.whynotpot.common.ModuleInfoModel
import com.whynotpot.third_party_module_manager.Common.MODULE_INFO_NAME
import com.whynotpot.third_party_module_manager.Common.PACKAGE_NAME
import com.whynotpot.third_party_module_manager.ModuleInfoExample
import com.whynotpot.third_party_module_manager.ViewModelManager
import com.whynotpot.third_party_module_manager.file.FileLoaderImpl
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.loader.LoaderType
import com.whynotpot.third_party_module_manager.loader.ModuleLoaderFactory
import com.whynotpot.third_party_module_manager.loader.ModulesLoader
import com.whynotpot.third_party_module_manager.loader.ModulesLoaderFakeSplitInstall
import java.io.File

class ModuleManagerImpl<T>(
    private val filePathType: FilePathType,
    val context: Context,//todo добавить строку с именеи если абсолют
    val viewModel: ViewModelManager? = null
) : ModuleManager<T> {
    private val externalModuleFileDir: File by lazy {
        FileLoaderImpl.get(
            filePathType,
            context = context
        )
    }
    private val modulesLoader: ModulesLoader by lazy {
        ModuleLoaderFactory.get(
            LoaderType.FAKE_SPLIT_LOADER,
            context,
            externalModuleFileDir,
            viewModel
        )
    }

    override fun load(name: String) {
        try {
            modulesLoader.installModule(name)
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message.toString())
        }
    }

    override fun loadListModule(): List<String> {
        try {
            return ModulesLoaderFakeSplitInstall.modulesList()
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message.toString())
        }
        return listOf()
    }

    fun getModalListWithInfo(): List<ModuleInfoModel<T>> {
        try {
            val installModuleName = ModulesLoaderFakeSplitInstall.modulesList()
            val existModuleName: List<String>? =
                externalModuleFileDir.listFiles { directory, filename ->
                    directory.length() > 0 && filename.endsWith(".apk")
                }?.toList()?.map { it.name.split('.')[0] }
            return existModuleName!!.map {
                if (it in installModuleName) {
                    moduleInfo(it)
                } else {
                    moduleInfoNull(it)
                }
            }
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message.toString())
        }
        return listOf()
    }

    override fun moduleInfo(name: String): ModuleInfoModel<T> {
        try {
            val moduleClass: Class<ModuleInfoModel<T>> =
                context.classLoader.loadClass("${PACKAGE_NAME}${name}${MODULE_INFO_NAME}") as Class<ModuleInfoModel<T>>
            return moduleClass.newInstance()
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message.toString())
        }
        return moduleInfoNull(name)
    }

    private fun moduleInfoNull(name: String): ModuleInfoModel<T> {
        return ModuleInfoExample<T>(name = name)
    }

    @Throws(Exception::class)
    fun executeModule(nameModule: String): T {
        try {
            val classLoader = context.classLoader
            val runs: Class<T> =
                classLoader.loadClass(moduleInfo(nameModule).className) as Class<T>
            return runs.newInstance()//todo fix runs
        } catch (e: Exception) {
            Log.i(this::class.simpleName, " ${e.message.toString()}")
            Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            throw Exception()
        }
    }


    override fun checkExist(name: String): ModuleManagerStatus {
        modulesLoader.modulesList()
        return ModuleManagerStatus.EXIST
    }

    override fun delete(name: String): ModuleManagerStatus {
        modulesLoader.deleteModule(name)
        return ModuleManagerStatus.DELETED
    }

    override fun enable(name: String) {
        TODO("Not yet implemented")
    }

    override fun loadModules(names: List<String>) {
        names.forEach { load(it) }
    }

}
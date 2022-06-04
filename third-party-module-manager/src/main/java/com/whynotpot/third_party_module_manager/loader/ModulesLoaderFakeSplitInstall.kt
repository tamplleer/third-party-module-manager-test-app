package com.whynotpot.third_party_module_manager.loader

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import java.io.File

@SuppressLint("StaticFieldLeak")
object ModulesLoaderFakeSplitInstall : ModulesLoader {
    lateinit var context: Context
    lateinit var externalFileDir: File
    private lateinit var moduleInstallRequest: SplitInstallRequest
    private val splitInstallManagerFake: FakeSplitInstallManager by lazy {
        FakeSplitInstallManagerFactory.create(
            context, externalFileDir
        )
    }

    fun init(context: Context, externalFileDir: File) {
        if (!this::context.isInitialized || !this::externalFileDir.isInitialized) {
            ModulesLoaderFakeSplitInstall.context = context
            ModulesLoaderFakeSplitInstall.externalFileDir = externalFileDir
            setupModulesDownload()
        }

    }

    private fun checkInitialisation() {
        if (!this::context.isInitialized || !this::externalFileDir.isInitialized) {
            throw Exception("Not initialized")
        }
    }


    override fun modulesList(): List<String> {
        checkInitialisation()
        splitInstallManagerFake.installedModules.forEach { Log.i("aa", "modules ${it}") }
        return splitInstallManagerFake.installedModules.toList()
    }

    private fun setupModulesDownload() {
        checkInitialisation()
        splitInstallManagerFake.registerListener {
            // 2
            when (it.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Log.i("aa", "DOWNLOADING")
                    //   binding.progressIndicator.visibility = View.VISIBLE
                    //    Toast.makeText(applicationContext, "Downloading", Toast.LENGTH_SHORT).show()
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Log.i("aa", "INSTALLED")
                    //   binding.progressIndicator.visibility = View.GONEToast.makeText(applicationContext, "Module Download Completed ohuet", Toast.LENGTH_SHORT).show()
                    // 3
                    /*   val intent = Intent()
                       intent.setClassName(
                           BuildConfig.APPLICATION_ID,
                           "com.whynotpot.cats.CatsActivity"
                       )
                       startActivity(intent)*/


                }
                SplitInstallSessionStatus.CANCELED -> {
                    Log.i("aa", "CANCELED")
                }
                SplitInstallSessionStatus.CANCELING -> {
                    Log.i("aa", "CANCELING")
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    Log.i("aa", "DOWNLOADED")
                }
                SplitInstallSessionStatus.FAILED -> {
                    Log.i("aa", "FAILED")
                }
                SplitInstallSessionStatus.INSTALLING -> {
                    Log.i("aa", "INSTALLING")
                }
                SplitInstallSessionStatus.PENDING -> {
                    Log.i("aa", "PENDING")
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    Log.i("aa", "REQUIRES_USER_CONFIRMATION")
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    Log.i("aa", "UNKNOWN")
                }
            }
        }
    }

    fun deleteModule(moduleName: String) {
        checkInitialisation()
        splitInstallManagerFake.deferredUninstall(listOf(moduleName));
    }

    fun deleteModule(modulesNames: List<String>) {
        checkInitialisation()
        splitInstallManagerFake.deferredUninstall(modulesNames);
    }

    override fun installModule(moduleName: String) {
        checkInitialisation()
        moduleInstallRequest = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()
        moduleInstallRequest.let {
            splitInstallManagerFake.startInstall(it)
        }

    }


}
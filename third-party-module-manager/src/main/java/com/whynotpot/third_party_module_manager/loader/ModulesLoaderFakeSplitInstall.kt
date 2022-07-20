package com.whynotpot.third_party_module_manager.loader

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.currentRecomposeScope
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import com.whynotpot.third_party_module_manager.ViewModelManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import java.io.File
import javax.inject.Inject

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

    fun init(context: Context, externalFileDir: File, viewModelManager: ViewModelManager?) {
        if (!this::context.isInitialized || !this::externalFileDir.isInitialized) {
            ModulesLoaderFakeSplitInstall.context = context
            ModulesLoaderFakeSplitInstall.externalFileDir = externalFileDir
            setupModulesDownload(viewModelManager)
        }

    }

    private fun checkInitialisation() {
        if (!this::context.isInitialized || !this::externalFileDir.isInitialized) {
            throw Exception("Not initialized")
        }
    }


    override fun modulesList(): List<String> {
        checkInitialisation()
        return splitInstallManagerFake.installedModules.toList()
    }

    private fun setupModulesDownload(viewModelManager: ViewModelManager?) {
        checkInitialisation()
        splitInstallManagerFake.registerListener {

            when (it.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Log.i(this::class.simpleName, "DOWNLOADING")
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Log.i(this::class.simpleName, "INSTALLED")
                    Toast.makeText(context, "Module instaled", Toast.LENGTH_SHORT).show()
                    viewModelManager?.setLoading(true)
                }
                SplitInstallSessionStatus.CANCELED -> {
                    Log.i(this::class.simpleName, "CANCELED")
                }
                SplitInstallSessionStatus.CANCELING -> {
                    Log.i(this::class.simpleName, "CANCELING")
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    Log.i(this::class.simpleName, "DOWNLOADED")
                }
                SplitInstallSessionStatus.FAILED -> {
                    Log.i(this::class.simpleName, "FAILED")
                }
                SplitInstallSessionStatus.INSTALLING -> {
                    Log.i(this::class.simpleName, "INSTALLING")
                }
                SplitInstallSessionStatus.PENDING -> {
                    Log.i(this::class.simpleName, "PENDING")
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    Log.i(this::class.simpleName, "REQUIRES_USER_CONFIRMATION")
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    Log.i(this::class.simpleName, "UNKNOWN")
                }
            }
        }
    }

    override fun deleteModule(moduleName: String) {
        checkInitialisation()
        splitInstallManagerFake.deferredUninstall(listOf(moduleName));
    }

    override fun deleteModule(modulesNames: List<String>) {
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
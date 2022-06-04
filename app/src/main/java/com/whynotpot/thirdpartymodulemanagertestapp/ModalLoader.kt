package com.whynotpot.thirdpartymodulemanagertestapp

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import com.whynotpot.frog.RunApi
import java.io.File

class ModalLoader(val context: Context, val externalFileDir: File) {
/*    private lateinit var moduleInstallRequest: SplitInstallRequest
    private val splitInstallManagerFake: FakeSplitInstallManager by lazy {
        FakeSplitInstallManagerFactory.create(
            context, externalFileDir
        )
    }

    fun modalsList(): List<String> {
        setupModulesDownload("frogs")
        Log.i("aa", "language ${moduleInstallRequest.moduleNames}")
        Log.i("aa", "language ${moduleInstallRequest.toString()}")
        return splitInstallManagerFake.installedModules.toList()
    }

    fun setupModulesDownload(moduleName: String) {
        moduleInstallRequest = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()
        Log.i("aa","setupModulesDownload")
// 1
        splitInstallManagerFake.registerListener {
            // 2
            when (it.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Log.i("aa","DOWNLOADING")
                    //   binding.progressIndicator.visibility = View.VISIBLE
                    //    Toast.makeText(applicationContext, "Downloading", Toast.LENGTH_SHORT).show()
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Log.i("aa","INSTALLED")
                    //   binding.progressIndicator.visibility = View.GONEToast.makeText(applicationContext, "Module Download Completed ohuet", Toast.LENGTH_SHORT).show()
                    // 3
                    *//*   val intent = Intent()
                       intent.setClassName(
                           BuildConfig.APPLICATION_ID,
                           "com.whynotpot.cats.CatsActivity"
                       )
                       startActivity(intent)*//*


                }
                SplitInstallSessionStatus.CANCELED -> {
                    Log.i("aa","CANCELED")
                    TODO()
                }
                SplitInstallSessionStatus.CANCELING -> {
                    Log.i("aa","CANCELING")
                    TODO()
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    Log.i("aa","DOWNLOADED")
                    TODO()
                }
                SplitInstallSessionStatus.FAILED -> {
                    Log.i("aa","FAILED")
                    TODO()
                }
                SplitInstallSessionStatus.INSTALLING -> {
                    Log.i("aa","INSTALLING")
                    TODO()
                }
                SplitInstallSessionStatus.PENDING -> {
                    Log.i("aa","PENDING")
                    // Toast.makeText(applicationContext, "Module cat error", Toast.LENGTH_SHORT).show()
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    Log.i("aa","REQUIRES_USER_CONFIRMATION")
                    TODO()
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    Log.i("aa","UNKNOWN")
                    TODO()
                }
            }
        }
        //    binding.catsCard.setOnClickListener {
        //       executeModule()
        //   splitInstallManagerFake.installedModules.forEach { Log.i("aa modules = ", it) }

        //  splitInstallManager.deferredUninstall(listOf("frogs"));
        //   splitInstallManagerFake.startInstall(catsModuleInstallRequest)
        //   }
    }

    fun deleteModule(moduleName: String) {
        splitInstallManagerFake.deferredUninstall(listOf(moduleName));
    }

    fun deleteModule(modulesNames: List<String>) {
        splitInstallManagerFake.deferredUninstall(modulesNames);
    }

    fun installModule(moduleName: String) {
        moduleInstallRequest.let {
            splitInstallManagerFake.startInstall(it)
        }

    }

    fun executeModule(classLoader: ClassLoader, supportFragmentManager: FragmentManager): Fragment {
        try {

            Log.i(
                "aa",
                "${classLoader.loadClass("com.whynotpot.frog.Run").classes.toList().toString()}"
            )
            val runs: Class<RunApi> =
                classLoader.loadClass("com.whynotpot.frog.Run") as Class<RunApi>
            val ss = runs.newInstance()
            Log.i("aa", "fROG =  ${ss.runString("mey mey")}")
            Log.i("aa", "fROG =  ${ss.run(1, 2).toString()}")
            return ss.getFragment()
        } catch (e: Exception) {
            Log.i("aa", "fRoG error ${e.message.toString()}")
            Log.i("aa", "fRoG error ${e.message.toString()}")
            throw Exception(e)
        }
    }*/
}
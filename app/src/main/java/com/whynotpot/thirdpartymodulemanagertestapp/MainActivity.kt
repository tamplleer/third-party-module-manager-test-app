package com.whynotpot.thirdpartymodulemanagertestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import com.whynotpot.third_party_module_manager.ModuleManager
import com.whynotpot.third_party_module_manager.ModulesManagerActivity
import com.whynotpot.thirdpartymodulemanagertestapp.databinding.ActivityMainBinding
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val name = "frogm"
    private lateinit var modalLoader: ModalLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.buttonLoad.setOnClickListener {      ModuleManager.load(this, name) }
        binding.buttonStart.setOnClickListener {      ModuleManager.load(this, name) }

        /*       val moduleInstallRequest: SplitInstallRequest= SplitInstallRequest.newBuilder()
                   .addModule(name)
                  // .addLanguage(Locale.forLanguageTag("en"))
                   .build()*/
        /* val file: File? = getExternalFilesDir("modals")
         if (file != null)
             modalLoader = ModalLoader(baseContext, file)
         else {
             Log.i("aa", "dasdasdasdasdas")
         }*/


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val intentToLibrary = Intent(
                this,
                ModulesManagerActivity::class.java
            )
            startActivity(intentToLibrary)
        }
        // Log.i("aa",modalLoader)
        /*    modalLoader.apply {
                modalsList()
                binding.buttonLoad.setOnClickListener {

                    installModule(name)
                }
                binding.buttonDelete.setOnClickListener { deleteModule(name) }
                binding.buttonStart.setOnClickListener {


                    supportFragmentManager.beginTransaction().apply {
                        replace(
                            binding.flFragment.id,
                            executeModule(classLoader, supportFragmentManager)
                        )
                        commit()
                    }
                }
            }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
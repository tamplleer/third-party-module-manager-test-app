package com.whynotpot.thirdpartymodulemanagertestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.whynotpot.common.RunApi
import com.whynotpot.third_party_module_manager.Common
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl
import com.whynotpot.third_party_module_manager.ModulesManagerActivity
import com.whynotpot.third_party_module_manager.ThirdPartyModuleManager
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.thirdpartymodulemanagertestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val name = "cat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Common.context = this
            // ThirdPartyModuleManager.moduleManager<RunApi>(FilePathType.APP_FOLDER, this)
       // ThirdPartyModuleManager.startListModuleActivity(this)
        val moduleManagerImpl = ModuleManagerImpl<RunApi>(FilePathType.APP_FOLDER, this)
        binding.buttonLoad.setOnClickListener { moduleManagerImpl.load(name) }
        binding.buttonStart.setOnClickListener {

            try {
                supportFragmentManager.beginTransaction().apply {
                    replace(
                        binding.flFragment.id,
                        moduleManagerImpl.executeModule(name).getFragment()
                    )
                    commit()
                }
            } catch (e: Exception) {
                Log.i(this::class.simpleName, e.message.toString())
            }

        }


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            val intentToLibrary = Intent(
                this, ModulesManagerActivity::class.java
            )
            startActivity(intentToLibrary)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
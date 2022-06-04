package com.whynotpot.thirdpartymodulemanagertestapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.whynotpot.frog.RunApi
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl
import com.whynotpot.third_party_module_manager.ModulesManagerActivity
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.thirdpartymodulemanagertestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val name = "frogm3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val moduleManagerImpl = ModuleManagerImpl<RunApi>(FilePathType.APP_FOLDER, name, this)
        binding.buttonLoad.setOnClickListener { moduleManagerImpl.load(name) }
        binding.buttonStart.setOnClickListener {


            supportFragmentManager.beginTransaction().apply {
                replace(
                    binding.flFragment.id,
                    moduleManagerImpl.executeModule().getFragment()
                )
                commit()
            }
        }



        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            val intentToLibrary = Intent(this, ModulesManagerActivity::class.java
            )
            startActivity(intentToLibrary)
        }
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
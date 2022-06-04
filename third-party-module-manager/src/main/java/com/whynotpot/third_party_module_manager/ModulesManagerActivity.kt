package com.whynotpot.third_party_module_manager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whynotpot.third_party_module_manager.ui.theme.PlayFeatureDeliveryTheme

class ModulesManagerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modalLoader = getExternalFilesDir("modals")?.let { ModuleLoader(baseContext, it) }
        ModuleManager.getModuleInfo(this)?.getModuleInfo()?.description
            ?.let { Log.i("aa", it) }
        setContent {
            PlayFeatureDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    modalLoader?.let { ListModule(items = it.modalsList()) }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ListModule(items: List<Any>) {
    LazyColumn {
        items(items.size) { i ->
            ModuleText(text = items[i].toString())
        }
    }
}

@Composable
fun ModuleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayFeatureDeliveryTheme {
        Greeting("Android")
    }
}
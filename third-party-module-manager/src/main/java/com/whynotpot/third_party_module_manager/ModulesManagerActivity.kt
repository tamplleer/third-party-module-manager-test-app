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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whynotpot.frog.ModuleInfoModel
import com.whynotpot.frog.RunApi
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl
import com.whynotpot.third_party_module_manager.ui.theme.PlayFeatureDeliveryTheme

class ModulesManagerActivity : ComponentActivity() {
    private val nameModule = "frogm3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moduleManagerImpl = ModuleManagerImpl<RunApi>(FilePathType.APP_FOLDER, nameModule, this)

        val moduleInfo =
            moduleManagerImpl.moduleInfo(nameModule)

        Log.i("aa", moduleInfo?.data?.runString("Yes cat man").toString())
        Log.i("aa", moduleInfo?.description.toString())


        setContent {
            PlayFeatureDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    ListModule(items = moduleManagerImpl.getModalListWithInfo())

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
fun ListModule(items: List<ModuleInfoModel<RunApi>>) {
    LazyColumn {
        items(items.size) { i ->
            ModuleText(text = items[i].name)
            ModuleTextSmall(text = items[i].description)
            ModuleTextSmallColor(text = items[i].className)
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

@Composable
fun ModuleTextSmall(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun ModuleTextSmallColor(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        color = Color.Gray,
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
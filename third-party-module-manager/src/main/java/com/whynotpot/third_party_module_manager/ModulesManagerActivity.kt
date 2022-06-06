package com.whynotpot.third_party_module_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whynotpot.common.ModuleInfoModel
import com.whynotpot.common.RunApi
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.manager.ModuleManager
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl
import com.whynotpot.third_party_module_manager.ui.theme.PlayFeatureDeliveryTheme

class ModulesManagerActivity : ComponentActivity() {
    private val nameModule = "frogm3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moduleManagerImpl = ModuleManagerImpl<RunApi>(FilePathType.APP_FOLDER,Common.context!!)
        moduleManagerImpl.checkExist(nameModule)

        setContent {
            PlayFeatureDeliveryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Greeting("Test app")
                        ListModule<RunApi>(//todo вынести RunApi заменить на дженерик
                            items = moduleManagerImpl.getModalListWithInfo(),
                            moduleManagerImpl
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "THis is $name!", style = MaterialTheme.typography.h3)
}

@Composable
fun <T> ListModule(items: List<ModuleInfoModel<T>>, moduleManager: ModuleManager<T>) {
    LazyColumn {
        items(items.size) { i ->
            NewsResourceCardExpanded<T>(
                items[i],
                CardModel("https://apppearl.com/wp-content/uploads/2021/12/Android1.jpg"),
                onClick = { moduleManager.load(items[i].name) },
                Modifier.padding(24.dp)
            )
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
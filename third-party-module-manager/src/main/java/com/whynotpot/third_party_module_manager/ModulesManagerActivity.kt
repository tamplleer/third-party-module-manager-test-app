package com.whynotpot.third_party_module_manager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Column
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
import com.whynotpot.common.ModuleInfoModel
import com.whynotpot.common.RunApi
import com.whynotpot.third_party_module_manager.file.FilePathType
import com.whynotpot.third_party_module_manager.manager.ModuleManager
import com.whynotpot.third_party_module_manager.manager.ModuleManagerImpl
import com.whynotpot.third_party_module_manager.ui.theme.PlayFeatureDeliveryTheme

class ModulesManagerActivity : ComponentActivity() {
    private val nameModule = "frogm3"
    private val viewModelManager: ViewModelManager by viewModels()
    companion object {
        fun startActivityModuleManager(context: Context) {
            val intent = Intent(
                context, ModulesManagerActivity::class.java
            )

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moduleManagerImpl = ModuleManagerImpl<RunApi>(FilePathType.APP_FOLDER, Common.context!!,viewModel =viewModelManager)
        moduleManagerImpl.checkExist(nameModule)
        setContent {
            PlayFeatureDeliveryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
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
    val viewModelManager = hiltViewModel<ViewModelManager>()
    LazyColumn {
        items(items.size) { i ->
            NewsResourceCardExpanded<T>(
                items[i],
                CardModel("https://i.pinimg.com/originals/48/ae/6b/48ae6bab405e24c5b6f3626e4b48bfeb.png"),
                onClick = { moduleManager.load(items[i].name) },
                Modifier.padding(24.dp),
                        viewModelManager
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
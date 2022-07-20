package com.whynotpot.third_party_module_manager

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.whynotpot.common.ModuleInfoModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CardModel(val headerImageUrl: String)

/*@Preview
@Composable
fun MyViewPreview() {
    NewsResourceCardExpanded(
        ModuleInfoExample(),
        CardModel("https://miro.medium.com/max/1400/1*GohlLEuYUivKZLom1AiJkg.png"),
        onClick = {})
}*/

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> NewsResourceCardExpanded(
    infoModel: ModuleInfoModel<T>,
    cardModel: CardModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModelManager: ViewModelManager = viewModel()
) {
    val clickActionLabel = stringResource(R.string.card_name)
    val isLoad by viewModelManager.isLoading.collectAsState()
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier.semantics {
            onClick(label = clickActionLabel, action = null)
        }
    ) {
        Column {
            Row() {
                if (!cardModel.headerImageUrl.isNullOrEmpty()) {
                    NewsResourceHeaderImage(cardModel.headerImageUrl)
                }
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    NewsResourceTitle(
                        infoModel.name,
                        modifier = Modifier.fillMaxWidth((.8f))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Permission()
                    Spacer(modifier = Modifier.height(12.dp))
                    NewsResourceShortDescription(infoModel.description)
                }
            }//infoModel.description == "None"

                if (!isLoad && infoModel.description=="None") {
                    buttonDownload(onClick, viewModelManager)
                } else {
                    buttonsEnableDelete()
                }
        }
    }
}

@Composable
fun NewsResourceHeaderImage(
    headerImageUrl: String?
) {
    AsyncImage(
        placeholder = if (LocalInspectionMode.current) {
            painterResource(androidx.appcompat.R.drawable.abc_ic_search_api_material)
        } else {
            null
        },
        modifier = Modifier
            .width(150.dp)
            .height(180.dp),
        contentScale = ContentScale.Crop,
        model = headerImageUrl,
        contentDescription = null
    )
}

@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier
) {
    Text(newsResourceTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun NewsResourceShortDescription(
    newsResourceShortDescription: String
) {
    Text(newsResourceShortDescription, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun buttonDownload(onClick: () -> Unit, viewModelManager: ViewModelManager) {
    val isLoading = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
            isLoading.value = !isLoading.value
            coroutineScope.launch {

                delay(500)
                onClick()
            }

        }) {
            Row() {
                Text(text = "Download", fontSize = 18.sp)
                if (!isLoading.value) {
                    Icon(
                        painterResource(id = R.drawable.ic_round_arrow_downward_24),
                        "Download",
                        tint = Color.White
                    )
                } else {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(6.dp)
                            .align(Alignment.Bottom)
                    )
                }
            }
        }
    }
}

@Composable
fun buttonsEnableDelete() {
    val isLoading = remember { mutableStateOf(false) }
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { isLoading.value = !isLoading.value }) {

            Row() {
                Text(text = "Delete", fontSize = 18.sp)
                if (!isLoading.value) {
                    Icon(
                        Icons.Rounded.Delete,
                        "Delete",
                        tint = Color.White
                    )
                } else {

                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(6.dp)
                            .align(Alignment.Bottom)
                    )

                }
            }
        }
        Button(onClick = { isLoading.value = !isLoading.value }) {

            Row() {
                Text(text = "Enable", fontSize = 18.sp)
                if (!isLoading.value) {
                    Icon(
                        Icons.Rounded.CheckCircle,
                        "Enable",
                        tint = Color.White
                    )
                } else {
                    Icon(
                        Icons.Rounded.AddCircle,
                        "Enable",
                        tint = Color.White
                    )
                }
            }


        }
    }
}

@Composable
fun Permission() {
    Row() {
        Icon(
            Icons.Rounded.Warning,
            "Enable",
            tint = Color.White
        )
        Icon(
            Icons.Rounded.Build,
            "Enable",
            tint = Color.White
        )
        Icon(
            Icons.Rounded.Favorite,
            "Enable",
            tint = Color.White
        )
    }

}
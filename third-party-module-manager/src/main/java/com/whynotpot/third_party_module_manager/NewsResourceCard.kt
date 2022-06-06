package com.whynotpot.third_party_module_manager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.whynotpot.common.ModuleInfoModel

data class CardModel(val headerImageUrl: String)

/*@Preview
@Composable
fun MyViewPreview() {
    NewsResourceCardExpanded(
        ModuleInfoExample(),
        CardModel("https://miro.medium.com/max/1400/1*GohlLEuYUivKZLom1AiJkg.png"),
        onClick = {})
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> NewsResourceCardExpanded(
    infoModel: ModuleInfoModel<T>,
    cardModel: CardModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickActionLabel = stringResource(R.string.card_name)
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier.semantics {
            onClick(label = clickActionLabel, action = null)
        }
    ) {
        Column {
            if (!cardModel.headerImageUrl.isNullOrEmpty()) {

                NewsResourceHeaderImage(cardModel.headerImageUrl)

            }
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        NewsResourceTitle(
                            infoModel.name,
                            modifier = Modifier.fillMaxWidth((.8f))
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    NewsResourceShortDescription(infoModel.description)
                }
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
            .fillMaxWidth()
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
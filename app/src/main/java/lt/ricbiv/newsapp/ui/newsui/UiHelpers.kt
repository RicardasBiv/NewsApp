package lt.ricbiv.newsapp.ui.newsui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import lt.ricbiv.newsapp.R

@Composable
fun Image(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(5.dp)
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            color = Color.Transparent,
            shape = shape
        ) {
            if (url.isNullOrEmpty()) {

                androidx.compose.foundation.Image(
                    painter = painterResource(R.drawable.image_not_found2),
                    contentDescription = "Error image"
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image_not_found2),
                    contentDescription = "article photo",
                    contentScale = contentScale,
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}
@Composable
fun imageForArticle(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(5.dp)
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            color = Color.Transparent,
            shape = shape
        ) {
            if (url!!.isNotEmpty())  {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    contentDescription = "article photo",
                    contentScale = contentScale,
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
fun HeightSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredHeight(value))
}

@Composable
fun WidthSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredWidth(value))
}

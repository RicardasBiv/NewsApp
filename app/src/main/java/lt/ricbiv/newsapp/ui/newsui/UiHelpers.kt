package lt.ricbiv.newsapp.ui.newsui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
        if (url.isNullOrEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Error image",
                tint = if (MaterialTheme.colors.isLight) Color.Black else Color.White
            )
        } else {
            Surface(
                color = Color.Transparent,
                shape = shape
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_error),
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

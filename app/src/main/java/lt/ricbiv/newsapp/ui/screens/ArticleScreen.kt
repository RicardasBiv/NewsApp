package lt.ricbiv.newsapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.models.Source
import lt.ricbiv.newsapp.ui.newsui.HeightSpacer
import lt.ricbiv.newsapp.ui.newsui.Image
import lt.ricbiv.newsapp.ui.newsui.WidthSpacer
import lt.ricbiv.newsapp.ui.newsui.imageForArticle
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme
import lt.ricbiv.newsapp.ui.theme.articleTitleStyle
import lt.ricbiv.newsapp.ui.theme.normalTextStyle
import lt.ricbiv.newsapp.utils.OpenInternetBrowser
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("SimpleDateFormat")
@Composable
fun ArticleScreen(article: Article) {

    val context = LocalContext.current
    val isLight = MaterialTheme.colors.isLight
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary,
    ) {
        Column {
            Row(
                modifier = Modifier.padding(all = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                if (!article.urlToImage.isNullOrEmpty()) {
                    imageForArticle(
                        url = article.urlToImage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(250.dp)
                    )
                }
            }
            HeightSpacer(value = 4.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = article.title,
                    textAlign = TextAlign.Center,
                    style = articleTitleStyle.copy(color = MaterialTheme.colors.onSurface),
                    maxLines = 3,
                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )

            }
            HeightSpacer(value = 4.dp)
            if (article.description != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Text(
                        text = article.description,
                        style = normalTextStyle.copy(color = MaterialTheme.colors.onSurface),
                        fontSize = 16.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (article.author != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp), horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = article.author,
                        style = normalTextStyle.copy(color = MaterialTheme.colors.onSurface),
                        fontWeight = FontWeight.Bold
                    )

                }
            }
            if(article.publishedAt != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp), horizontalAlignment = Alignment.End
                ) {
                    val parser = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'")
                    val formatter = SimpleDateFormat("yyyy-mm-dd HH:mm")
                    val date = formatter.format(parser.parse(article.publishedAt)!!)
                    Text(
                        text = date,
                        style = normalTextStyle.copy(color = MaterialTheme.colors.onSurface),
                        fontWeight = FontWeight.Bold
                    )

                }
            }
            WidthSpacer(value = 10.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            ) {
                Button(
                    onClick = { OpenInternetBrowser.launch(context, article.url) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = if(isLight) Color.White else MaterialTheme.colors.onSurface)
                ) {
                    Text(
                        text = stringResource(id = lt.ricbiv.newsapp.R.string.read_full_article).uppercase(),
                        color = if(isLight)MaterialTheme.colors.onSurface else Color.Black
                    )

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticlePreview(){
    NewsAppTheme {
        ArticleScreen(Article(Source("1","bele"),"GOOGLE","TESTINIS teksto reik prirasyti daugiau kad matytusi kaip parsinasi visas tekstas ir kaip kas atrodo ","Trumpa santrauka","","https://images.wsj.net/im-741981/social","2023-03-14","Daug teksto sdlfkjsdlfjsdflsdjflsdkfjsdl'fh sdf sdlkjf sldkfjds lfsdfdsfjlsdflsd"))

    }
}















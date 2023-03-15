package lt.ricbiv.newsapp.ui.newsui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.ui.theme.articleTitleStyle
import lt.ricbiv.newsapp.ui.theme.normalTextStyle

@Composable
fun ArticleObject(article: Article, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("Debug","url: ${article.urlToImage}")
            Image(
                url = article.urlToImage,
                modifier = Modifier.requiredSize(100.dp)
            )
            WidthSpacer(value = 10.dp)
            Column {
                if (!article.source.name.isNullOrEmpty()) {
                    Text(
                        text = article.source.name,
                        style = articleTitleStyle.copy(color = MaterialTheme.colors.secondary)
                    )
                    HeightSpacer(value = 4.dp)
                }
                Text(
                    text = article.title,
                    style = articleTitleStyle.copy(color = MaterialTheme.colors.onSurface),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                    text = article.publishedAt!!.substring(0, 10),
                    style = normalTextStyle.copy(color = MaterialTheme.colors.onSurface)
                )
            }
        }
        HeightSpacer(value = 10.dp)
        Divider(
            color = MaterialTheme.colors.secondary.copy(
                alpha = 0.2f
            )
        )
    }
}

@Composable
fun ArticleList(articles: List<Article>, navigateToArticle: (Article) -> Unit) {
    LazyColumn {
        items(articles) { article ->
            ArticleObject(
                article = article,
                onClick = {
                    navigateToArticle(article)
                }
            )
        }
    }
}


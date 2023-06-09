package lt.ricbiv.newsapp.ui.newsui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.ui.theme.articleTitleStyle
@Composable
fun NewsListContainer(
    navigateToArticle: (Article) -> Unit,
    response: Resource<NewsResponse>,
    retry: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
    ) {
        when (response) {
            is Resource.Loading -> {
            }
            is Resource.Error -> {
                ErrorView(
                    errorMessage = response.message!!,
                    showRetry = true,
                    retry = retry
                )
            }
            is Resource.Success -> {
                if (response.data?.articles!!.isNotEmpty()) {
                    ArticleList(
                        articles = response.data.articles,
                        navigateToArticle = navigateToArticle
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorView(
    errorMessage: String,
    showRetry: Boolean,
    retry: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = articleTitleStyle.copy(color = MaterialTheme.colors.onSurface)
        )
        if (showRetry) {
            TextButton(onClick = retry) {
                Text(
                    text = stringResource(id = lt.ricbiv.newsapp.R.string.retry),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }
        }
    }
}
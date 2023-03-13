package lt.ricbiv.newsapp.ui.newsui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import lt.ricbiv.newsapp.R
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.viewmodels.NewsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsUi(viewModel: NewsViewModel) {

    val articleList = viewModel.popularArticles.observeAsState().value!!

    Scaffold(
        content = {
            BodyContent(
                newsResponseResource = articleList,
                onThemeSwitch = {},
                tryAgain = {viewModel.runAction(NewsViewModel.Action.GetArticles)}
            )
        }
    )
}

@Composable
fun BodyContent(
    newsResponseResource: Resource<NewsResponse>,
    onThemeSwitch: () -> Unit,
    tryAgain: () -> Unit
) {
    val stringRes = R.string.top_Articles
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(stringRes, onThemeSwitch = {
                onThemeSwitch()
            })
            NewsListContainer(
                response = newsResponseResource,
                retry = {
                    tryAgain()
                }
            )
        }
    }
}
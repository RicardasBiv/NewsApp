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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.squareup.moshi.Moshi
import lt.ricbiv.newsapp.R
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.ui.navigation.Screen
import lt.ricbiv.newsapp.viewmodels.NewsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(navController: NavController) {

    val viewModel = hiltViewModel<NewsViewModel>()
    val articleList = viewModel.popularArticles.observeAsState().value!!

    fun navigateToArticle(article: Article){
        navController.currentBackStackEntry?.savedStateHandle?.set(key = "article",article)
        navController.navigate(Screen.More.route)
    }

    Scaffold(
        content = {
            BodyContent(
                newsResponseResource = articleList,
                tryAgain = { viewModel.runAction(NewsViewModel.Action.GetArticles) },
                navigateToArticle = { navigateToArticle(it) }
            )
        }
    )
}


@Composable
private fun BodyContent(
    navigateToArticle: (Article) -> Unit,
    newsResponseResource: Resource<NewsResponse>,
    tryAgain: () -> Unit
) {
    val stringRes = R.string.top_Articles
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary,
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(stringRes)
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = newsResponseResource is Resource.Loading)
            SwipeRefresh(state = swipeRefreshState , onRefresh = {tryAgain()}){
            NewsListContainer(
                response = newsResponseResource,
                retry = { tryAgain() },
                navigateToArticle = navigateToArticle
            )
        }
        }
    }
}
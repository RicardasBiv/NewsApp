package lt.ricbiv.newsapp.activities

import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme
import lt.ricbiv.newsapp.utils.Settings
import lt.ricbiv.newsapp.viewmodels.NewsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<NewsViewModel>()
    var list: List<Article>? = listOf()

    @Inject
    lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Add your newsapi.org key here
        sp.edit().putString(Settings.AUTH_TOKEN,"1a16811dd25348b891db420dc03bb8ad").apply()



        viewModel.popularArticles.observe(this){
            Log.d("Debug","Data ${it.data.toString()}")
            Log.d("Debug","Error: ${it.message}")
            list = it.data?.articles
        }

        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                }
            }
        }
    }
}

@Composable
fun NewsFeed() {
    var itemList =

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list.size) { index ->
            val item = newsItems[index]
            Article(item)
        }
    }
}

@Composable
fun NewsItem(item: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${item.author} | ${item.publishedAt}",
            color = Color.Gray,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.content,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
    }



}
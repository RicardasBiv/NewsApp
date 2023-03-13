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
import lt.ricbiv.newsapp.ui.newsui.NewsUi
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme
import lt.ricbiv.newsapp.utils.Settings
import lt.ricbiv.newsapp.viewmodels.NewsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<NewsViewModel>()

    @Inject
    lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Add your newsapi.org key here
        sp.edit().putString(Settings.AUTH_TOKEN,"1a16811dd25348b891db420dc03bb8ad").apply()
        setContent {
            NewsAppTheme {
                NewsUi(viewModel = viewModel)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
    }



}
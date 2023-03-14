package lt.ricbiv.newsapp.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import lt.ricbiv.newsapp.ui.SetupNavGraph
import lt.ricbiv.newsapp.ui.newsui.NewsScreen
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme
import lt.ricbiv.newsapp.utils.Settings
import lt.ricbiv.newsapp.viewmodels.NewsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private val viewModel by viewModels<NewsViewModel>()

    @Inject
    lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Add your newsapi.org key here
        sp.edit().putString(Settings.AUTH_TOKEN,"1a16811dd25348b891db420dc03bb8ad").apply()
        setContent {
            NewsAppTheme() {
                navController = rememberNavController()
                SetupNavGraph(navController)
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
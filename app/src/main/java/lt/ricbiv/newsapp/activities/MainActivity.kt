package lt.ricbiv.newsapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme
import lt.ricbiv.newsapp.viewmodels.NewsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<NewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.popularArticles.observe(this){
            Log.d("Debug","Data ${it.data.toString()}")
            Log.d("Debug","Error: ${it.message}")
        }

        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        Greeting("Android")
    }
}
package lt.ricbiv.newsapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import lt.ricbiv.newsapp.ui.SetupNavGraph
import lt.ricbiv.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        setContent {
            NewsAppTheme(darkTheme = isSystemInDarkTheme()) {
                navController = rememberNavController()
                setupScreenTracking()
                SetupNavGraph(navController)
            }
        }
    }
    private fun setupScreenTracking() {
        navController.addOnDestinationChangedListener{ _,destination,_ ->
            val params = Bundle()
            params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.label as String?)
            params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, destination.label as String?)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
    }


}
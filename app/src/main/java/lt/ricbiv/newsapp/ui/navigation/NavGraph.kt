package lt.ricbiv.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.squareup.moshi.Moshi
import lt.ricbiv.newsapp.MyApplication
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.ui.newsui.NewsScreen
import lt.ricbiv.newsapp.ui.screens.ArticleScreen

import lt.ricbiv.newsapp.ui.navigation.Screen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            NewsScreen(navController)
        }
        composable(
            route = Screen.More.route
        ) {
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            if (article != null) {
                ArticleScreen(article)
            }
            }
        }
    }
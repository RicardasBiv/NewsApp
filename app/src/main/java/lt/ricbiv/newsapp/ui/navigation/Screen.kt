package lt.ricbiv.newsapp.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen(route = "home_screen")
    object More: Screen(route = "more_screen")

}

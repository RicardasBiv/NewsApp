package lt.ricbiv.newsapp.repositories

import android.util.Log
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.api.responses.NewsSourcesResponse
import lt.ricbiv.newsapp.api.services.NewsApiService
import lt.ricbiv.newsapp.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor (private val service : NewsApiService) : BaseRepository() {

    suspend fun getTopArticles(country:String) : Resource<NewsResponse> {
        Log.d("GetTopArticles","Hit")
        return safeApiCall { service.getTopHeadlines(country = country, category = null, sources = null, q = null, pageSize = null, page = null) }
    }
}
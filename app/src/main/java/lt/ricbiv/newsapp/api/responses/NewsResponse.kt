package lt.ricbiv.newsapp.api.responses

import com.squareup.moshi.Json
import lt.ricbiv.newsapp.models.Article

data class NewsResponse(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "totalResults") val totalResults: Int,
    @field:Json(name = "articles") val articles : List<Article> = listOf()
)

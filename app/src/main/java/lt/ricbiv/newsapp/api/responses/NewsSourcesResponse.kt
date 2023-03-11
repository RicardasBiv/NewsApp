package lt.ricbiv.newsapp.api.responses

import com.squareup.moshi.Json
import lt.ricbiv.newsapp.models.Article
import lt.ricbiv.newsapp.models.Source

data class NewsSourcesResponse(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "sources") val sources : List<Source>
)
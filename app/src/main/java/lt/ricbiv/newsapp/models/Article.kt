package lt.ricbiv.newsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class Article(
    @field:Json(name = "source") val source: Source,
    @field:Json(name = "author") val author: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "urlToImage") val urlToImage: String?,
    //Save as string for now, because moshi don't have adapter for LocalDateTime
    @field:Json(name = "publishedAt") val publishedAt: String,
    @field:Json(name = "content") val content: String
) : Parcelable
package lt.ricbiv.newsapp.api.responses

import com.squareup.moshi.Json

data class ErrorResponse(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "code") val code: String,
    @field:Json(name = "message") val message: String,
)

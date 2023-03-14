package lt.ricbiv.newsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Source(
    @field:Json(name = "id") val id : String?,
    @field:Json(name = "name") val name : String?
) : Parcelable

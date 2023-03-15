package lt.ricbiv.newsapp

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.File

class TestDataClassGenerator {
    val moshi = Moshi.Builder().build()

    //generic function to  generate data classes from json file path
    private inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return jsonAdapter.fromJson(json)!!
    }

    fun getSuccessArticleDataResponse(): Response<NewsResponse>{
        val jsonString = getJson("NewsResponse.json")
        return Response.success(buildDataClassFromJson(jsonString))
    }

    fun getSuccessArticleDataResource(): Resource<NewsResponse>{
        val jsonString = getJson("NewsResponse.json")
        return Resource.Success(buildDataClassFromJson(jsonString))
    }

    fun getErrorArticleDataResponse(): Response<NewsResponse>{
        val jsonString = getJson("NewsFailedResponse.json")
        return Response.error(404,  jsonString.toResponseBody())
    }

    fun getErrorArticleDataResource(): Resource<NewsResponse>{
        val jsonString = getJson("NewsFailedResponse.json")
        return Resource.Error("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.")
    }




    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/$resourceName")

        return String(file.readBytes())
    }
}
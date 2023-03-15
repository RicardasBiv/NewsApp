package lt.ricbiv.newsapp.api.services

import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.api.responses.NewsSourcesResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String? = null,
        @Query("country") country: String? = null,
        @Query("sources") sources: String? = null,
        @Query("q") q: String? = null,
        @Query("pagesize") pageSize: Int? = null,
        @Query("page") page: Int?
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q: String?,
        @Query("sources") sources: String?,
        @Query("domains") domains: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("pageSize") pageSize: Int?,
        @Query("page") page: Int?
    ): Response<NewsResponse>

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String?,
        @Query("language") language: String?,
        @Query("country") country: String?
    ): Response<NewsSourcesResponse>
}
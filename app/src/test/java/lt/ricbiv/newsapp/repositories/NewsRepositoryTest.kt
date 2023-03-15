package lt.ricbiv.newsapp.repositories

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import lt.ricbiv.newsapp.TestDataClassGenerator
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.api.services.NewsApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryTest {

    @RelaxedMockK
    lateinit var newsApiService: NewsApiService

    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = NewsRepository(newsApiService)
    }

    @Test
    fun `test getTopArticles with success`() {
        coEvery {
            newsApiService.getTopHeadlines(
                country = "in",
                category = "test",
                page = 1
            )
        } returns TestDataClassGenerator().getSuccessArticleDataResponse()
        runBlocking {
            val response = repository.getTopArticles("test")
            assertEquals(2, response.data!!.articles.count() ?: 0)
        }
    }

    @Test
    fun `test getTopArticles with success but list is empty`() {
        coEvery {
            newsApiService.getTopHeadlines(
                country = "in",
                category = "test",
                page = 1
            )
        } returns Response.success(
            NewsResponse("ok", 0, listOf())
        )
        runBlocking {
            val response = repository.getTopArticles("test")
            assertEquals(0, response.data!!.articles.count())
        }
    }

    @Test
    fun `test getTopArticles not successfully`() {
        coEvery {
            newsApiService.getTopHeadlines(
                country = "in",
                category = "test",
                page = 1
            )
        } returns TestDataClassGenerator().getErrorArticleDataResponse()
        runBlocking {
            val response = repository.getTopArticles("test")
            assertEquals("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.", response.message )
        }
    }
}
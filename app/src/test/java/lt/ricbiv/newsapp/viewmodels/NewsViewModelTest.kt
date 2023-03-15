package lt.ricbiv.newsapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import lt.ricbiv.newsapp.TestDataClassGenerator
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.repositories.NewsRepository
import lt.ricbiv.newsapp.utils.MainDispatcherRule
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    @RelaxedMockK
    lateinit var newsRepository: NewsRepository

    lateinit var newsViewModel: NewsViewModel
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        newsViewModel = NewsViewModel(newsRepository)
    }


    @Test
    fun `test runAction(getArticles) with success`() = runTest {
        coEvery {
            newsRepository.getTopArticles(
                category = "business"
            )
        } returns TestDataClassGenerator().getSuccessArticleDataResource()
        runBlocking {
            newsViewModel.runAction(NewsViewModel.Action.GetArticles)
            advanceUntilIdle()
            Assert.assertEquals(2, newsViewModel.popularArticles.value!!.data!!.totalResults)
        }
    }

    @Test
    fun `test runAction(getArticles) with success but list is empty`() = runTest {
        coEvery {
            newsRepository.getTopArticles(
                category = "business",
            )
        } returns Resource.Success(NewsResponse("ok", 0, listOf()))
        runBlocking {
            newsViewModel.runAction(NewsViewModel.Action.GetArticles)
            advanceUntilIdle()
            Assert.assertEquals(true, newsViewModel.popularArticles.value!!.data!!.articles.isEmpty())
        }
    }

    @Test
    fun `test runAction(getArticles) not successfully`() = runTest {
        coEvery {
            newsRepository.getTopArticles(
                category = "business",
            )
        } returns TestDataClassGenerator().getErrorArticleDataResource()
        runBlocking {
            newsViewModel.runAction(NewsViewModel.Action.GetArticles)
            advanceUntilIdle()
            Assert.assertEquals(
                "Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.",
                newsViewModel.popularArticles.value!!.message
            )
        }
    }

    @Test
    fun `test when getArticles loading state is enabled`() = runTest {
        val states = mutableListOf<Resource<NewsResponse>>()

        newsViewModel.popularArticles.observeForever {
            states.add(it)
        }
        coEvery {
            newsRepository.getTopArticles(
                category = "business"
            )
        } returns TestDataClassGenerator().getSuccessArticleDataResource()
        runBlocking {
            newsViewModel.runAction(NewsViewModel.Action.GetArticles)
            advanceUntilIdle()
            //First when initializing object
            assert(states[0] is Resource.Loading)
            //when calling function
            assert(states[1] is Resource.Loading)
            assert(states[2] is Resource.Success)
        }
    }

}
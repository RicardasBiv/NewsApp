package lt.ricbiv.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.NewsResponse
import lt.ricbiv.newsapp.repositories.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _popularArticles = MutableLiveData<Resource<NewsResponse>>()
    val popularArticles: LiveData<Resource<NewsResponse>> = _popularArticles

    init {
        getPopularArticles()
    }


    private fun getPopularArticles() = viewModelScope.launch {
        _popularArticles.postValue(Resource.Loading())

        _popularArticles.postValue(newsRepository.getTopArticles("en"))
    }
}
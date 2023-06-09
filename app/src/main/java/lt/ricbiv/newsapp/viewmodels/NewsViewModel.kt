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

    private val _topArticles = MutableLiveData<Resource<NewsResponse>>(Resource.Loading())
    val popularArticles: LiveData<Resource<NewsResponse>> = _topArticles

    init {
        getTopArticles()
    }
    private fun getTopArticles() {
        viewModelScope.launch {
            //Loading state
            _topArticles.postValue(Resource.Loading())
            _topArticles.postValue(newsRepository.getTopArticles("business"))
        }
    }

    sealed class Action {
        object GetArticles : Action()
    }

    fun runAction(action: Action){
        when(action) {
            is Action.GetArticles -> {
                getTopArticles()
            }
        }
    }
}
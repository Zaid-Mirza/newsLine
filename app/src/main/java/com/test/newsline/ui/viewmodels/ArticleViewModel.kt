package com.test.newsline.ui.viewmodels

import androidx.lifecycle.*
import com.test.newsline.models.GeneralResponse
import com.test.newsline.models.Resource
import com.test.newsline.repositories.ArticleRepository
import com.test.newsline.utils.AppEnums
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    private val articlesLiveData = MutableLiveData<Resource<GeneralResponse>?>()

    fun getArticles(section: String, days: String): LiveData<Resource<GeneralResponse>?> {


        val handler = CoroutineExceptionHandler { data, exception ->
            val ss = ""
            articlesLiveData.postValue(Resource.error("", null, AppEnums.ErrorType.Service))

        }
        viewModelScope.launch(handler) {
            repository.getArticles(section, days) {
                articlesLiveData.postValue(it)
            }
        }
        return articlesLiveData
    }
}


class ArticleViewModelFactory(private val repository: ArticleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


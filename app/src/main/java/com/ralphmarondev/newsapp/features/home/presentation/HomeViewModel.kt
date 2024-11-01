package com.ralphmarondev.newsapp.features.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.ralphmarondev.newsapp.core.utils.Constant

class HomeViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        fetchNewsTopHeadLines()
    }

    fun fetchNewsTopHeadLines() {
        val newsApiClient = NewsApiClient(Constant.API_KEY)
        val request = TopHeadlinesRequest.Builder().language("en").build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.forEach {
                    Log.d("RESPONSE", "${it.title}: ${it.publishedAt}")
                }
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    throwable.localizedMessage?.let { Log.e("RESPONSE", it) }
                }
            }
        })
    }
}
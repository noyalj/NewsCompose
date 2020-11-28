package com.noyalj.newscompose.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.noyalj.newscompose.data.model.Article
import com.noyalj.newscompose.domain.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    private var cachedNews : Flow<PagingData<Article>>? =null


    fun getLatestNews(): Flow<PagingData<Article>> {

        if (cachedNews != null)
            return cachedNews!!

        val latestNews = newsRepository.getLatestNews("us").cachedIn(viewModelScope)
        cachedNews = latestNews
        return latestNews

    }


}
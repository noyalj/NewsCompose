package com.noyalj.newscompose.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.noyalj.newscompose.data.model.Article
import com.noyalj.newscompose.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    private val countryCode: String
) :
    ViewModel() {


    fun getLatestNews(): Flow<PagingData<Article>> {
        return newsRepository.getLatestNews(countryCode).cachedIn(viewModelScope)
    }


}
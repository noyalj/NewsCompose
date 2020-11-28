package com.noyalj.newscompose.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.noyalj.newscompose.data.NewsService
import com.noyalj.newscompose.data.model.Article
import com.noyalj.newscompose.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsService: NewsService) {

     fun getLatestNews(countryCode: String): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE,enablePlaceholders = false),
            pagingSourceFactory = { NewsSource(newsService,countryCode)}
        ).flow

    }
}
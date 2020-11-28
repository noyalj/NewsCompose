package com.noyalj.newscompose.domain

import androidx.paging.PagingSource
import com.noyalj.newscompose.data.NewsService
import com.noyalj.newscompose.data.model.Article

class NewsSource(
    val newsService: NewsService,
    private val countryCode: String
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            val page = params.key ?: 1
            val newsResponse = newsService.getLatestNews(countryCode, page)

            LoadResult.Page(
                data = newsResponse.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
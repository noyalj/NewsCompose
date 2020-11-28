package com.noyalj.newscompose.data

import com.noyalj.newscompose.data.model.Article
import com.noyalj.newscompose.data.model.NewsResponse
import com.noyalj.newscompose.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getLatestNews(
        @Query("country")
        countryCode:String = "us",
        @Query("page")
        page: Int?,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): NewsResponse
}
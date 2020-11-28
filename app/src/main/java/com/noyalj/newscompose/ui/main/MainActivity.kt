package com.noyalj.newscompose.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import com.noyalj.newscompose.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.noyalj.newscompose.ui.items.NewsItem
import com.noyalj.newscompose.theming.NewsComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsComposeTheme {
                ProvideWindowInsets {
                    MainCompose(mainViewModel = mainViewModel)
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun MainCompose(mainViewModel: MainViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Latest News",
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 0.dp)
                    )
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            asset = vectorResource(id = R.drawable.ic_baseline_offline_bolt_24),
//                            tint = MaterialTheme.colors.secondary
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {}) {
//                        Icon(asset = vectorResource(id = R.drawable.ic_baseline_search_24))
//                    }
//                }
            )
        },
        bodyContent = {
            NewsData(mainViewModel = mainViewModel)
        }
    )


}

@ExperimentalCoroutinesApi
@Composable
fun NewsData(mainViewModel: MainViewModel) {

    val articles = mainViewModel.getLatestNews().collectAsLazyPagingItems()

    LazyColumn {
        items(articles) { article ->
            NewsItem(article = article!!)
        }

        articles.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {

                    ShowProgress()
                }
                loadState.append is LoadState.Loading -> item {
                    ShowProgress()
                }
                loadState.refresh is LoadState.Error -> item {

                    Snackbar(
                        text = { Text(text = "Error retrieving latest news ") },
                        shape = RoundedCornerShape(0.dp)
                    )


                }
            }
        }
    }

}

@Composable
fun ShowProgress() {


    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

}
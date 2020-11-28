package com.noyalj.newscompose.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Strings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.ui.tooling.preview.Preview
import com.google.android.material.appbar.AppBarLayout
import com.noyalj.newscompose.components.NewsItem
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

    Column {
        TopAppBar(
            title = { Text(text = "Latest News") },
            backgroundColor = MaterialTheme.colors.onPrimary,

        )
        NewsData(mainViewModel = mainViewModel)
    }
}

@ExperimentalCoroutinesApi
@Composable
fun NewsData(mainViewModel: MainViewModel) {

    val articles = mainViewModel.getLatestNews().collectAsLazyPagingItems()

    LazyColumn{
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
package com.noyalj.newscompose.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noyalj.newscompose.data.model.Article
import dev.chrisbanes.accompanist.coil.CoilImage


@Composable
fun NewsItem(article: Article) {

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 8.dp).weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            article.title?.let { title ->
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onSurface,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            article.source?.let { source ->
                Text(
                    text = source.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.preferredSize(88.dp).padding(8.dp),
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            article.urlToImage?.let {
                CoilImage(
                    data = article.urlToImage,
                    contentScale = ContentScale.Crop,
                    fadeIn = true,

                    modifier = Modifier.fillMaxSize()
                        .align(alignment = Alignment.Top)
                )
            }


        }
    }
}


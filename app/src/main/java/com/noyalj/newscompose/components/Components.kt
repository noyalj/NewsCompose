package com.noyalj.newscompose.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    article.urlToImage?.let {
        Card(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
        ) {

            Column {


                CoilImage(
                    data = it,
                    contentScale = ContentScale.Crop,
                    fadeIn = true,
                    modifier = Modifier.fillMaxWidth().preferredHeight(272.dp)
                )


                article.source?.name?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                article.title?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)

                    )
                }


            }
        }
    }

}


package com.pagingcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pagingcompose.model.Results
import com.pagingcompose.viewModel.QuoteViewModel

@Composable
fun ShowQuotes(quoteViewModel: QuoteViewModel) {
    val quoteData: LazyPagingItems<Results> =
        quoteViewModel.data.collectAsLazyPagingItems()

    when (quoteData.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingFirstItem()
        }

        is LoadState.Error -> {

        }

        is LoadState.NotLoading -> Unit
    }

    LazyColumn {
        items(count = quoteData.itemCount) { index ->
            Text(
                text = quoteData[index]?.content ?: "",
                fontSize = 20.sp,
                modifier = Modifier.padding(15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        when (quoteData.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }

            is LoadState.Error -> {
                item {
                    LoadError()
                }
            }

            is LoadState.NotLoading -> Unit
        }
    }
}

@Composable
fun LoadError() {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Server Error occurred",
            color = Color.White,
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(Color.Red)
                .padding(15.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun LoadingFirstItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Magenta
        )
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Magenta
        )
    }
}
package com.ralphmarondev.newsapp.features.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ralphmarondev.newsapp.features.home.presentation.HomeViewModel

@Composable
fun CategoriesBar(viewModel: HomeViewModel) {
    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )
    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { Spacer(modifier = Modifier.width(2.dp)) }
        item {
            if (isSearchExpanded) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color.Gray, CircleShape)
                        .clip(CircleShape),
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AnimatedVisibility(searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Clear,
                                        contentDescription = "Clear"
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            IconButton(
                                onClick = {
                                    isSearchExpanded = !isSearchExpanded
                                    if (searchQuery.isNotEmpty()) {
                                        viewModel.fetchEverythingWithQuery(searchQuery)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "Search"
                                )
                            }
                        }

                    },
                    placeholder = {
                        Text(
                            text = "Search News",
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace
                    )
                )
            } else {
                IconButton(onClick = { isSearchExpanded = !isSearchExpanded }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search"
                    )
                }
            }
        }
        items(categoriesList.size) { index ->
            ElevatedButton(
                onClick = {
                    viewModel.fetchNewsTopHeadLines(categoriesList[index])
                },
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 2.dp)
            ) {
                Text(
                    text = categoriesList[index],
                    fontFamily = FontFamily.Monospace
                )
            }
        }
        item { Spacer(modifier = Modifier.width(2.dp)) }
    }
}
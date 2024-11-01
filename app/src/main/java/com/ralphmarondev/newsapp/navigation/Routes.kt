package com.ralphmarondev.newsapp.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data class Details(val url: String)
}
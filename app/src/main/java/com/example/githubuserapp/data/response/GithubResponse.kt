package com.example.githubuserapp.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class GithubResponse(
    val total_count: Int,
    val incomplete_result: Boolean,
    val items: List<GithubData>
)

@Parcelize
data class GithubData(
    val avatar_url: String,
    val login: String,
    val html_url: String
): Parcelable



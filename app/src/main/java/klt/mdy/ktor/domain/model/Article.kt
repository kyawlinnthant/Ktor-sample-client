package klt.mdy.ktor.domain.model

data class Article(
    val title: String,
    val author: String,
    val description: String,
    val content: String,
    val publishedDate: String,
    val source: String,
    val webLink: String,
    val imageUrl: String
)

package klt.mdy.ktor.data.remote.dto

import klt.mdy.ktor.domain.model.Article
import klt.mdy.ktor.util.DateFormatter
import kotlinx.serialization.Serializable

@Serializable
data class NewsDTO(
    val status: String = "error",
    val totalResults: Int = -1,
    val articles: List<ArticleData> = listOf()
)

@Serializable
data class ArticleData(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceData,
    val title: String,
    val url: String,
    val urlToImage: String
) {
    fun toVo(): Article {
        return Article(
            title = title,
            author = author,
            description = description,
            content = content,
            publishedDate = DateFormatter.convertStringToReadableDate(publishedAt),
            source = source.name,
            webLink = url,
            imageUrl = urlToImage
        )
    }
}

@Serializable
data class SourceData(
//    val id: String? = null,
    val name: String
)
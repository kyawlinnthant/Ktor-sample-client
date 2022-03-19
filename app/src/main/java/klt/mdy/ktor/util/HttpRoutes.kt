package klt.mdy.ktor.util

object HttpRoutes {
    private const val BASE_URL = "https://newsapi.org/v2"
    const val NEWS = "$BASE_URL/top-headlines"
    const val Q_LANGUAGE = "language"
    const val Q_SORT_BY = "sortBy"
    const val Q_PAGE_SIZE = "pageSize"
    const val Q_PAGE = "page"
    const val HEADER_KEY = "X-Api-Key"

}
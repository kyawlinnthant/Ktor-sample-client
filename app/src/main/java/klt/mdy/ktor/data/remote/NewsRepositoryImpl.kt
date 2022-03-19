package klt.mdy.ktor.data.remote

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import klt.mdy.ktor.BuildConfig
import klt.mdy.ktor.data.remote.dto.NewsDTO
import klt.mdy.ktor.util.HttpRoutes

class NewsRepositoryImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun getTopHeadlines(): NewsDTO {
        return try {
            client.get {
                url(HttpRoutes.NEWS)
                headers{
                    append(HttpRoutes.HEADER_KEY,BuildConfig.API_KEY)
                }
                parameter(HttpRoutes.Q_LANGUAGE, "en")
                parameter(HttpRoutes.Q_SORT_BY, "publishedAt")
                parameter(HttpRoutes.Q_PAGE_SIZE, 100)
                parameter(HttpRoutes.Q_PAGE, 1)
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            println(e.response.status.description)
            NewsDTO()
        } catch (e: ClientRequestException) {
            // 4xx
            println(e.response.status.description)
            NewsDTO()
        } catch (e: ServerResponseException) {
            // 5xx
            println(e.response.status.description)
            NewsDTO()
        } catch (e: Exception) {
            NewsDTO()
        }
    }

    override suspend fun createTopHeadlines(): NewsDTO? {
        return try {

            client.post<NewsDTO> {
                url(HttpRoutes.NEWS)
                headers{
                    append(HttpRoutes.HEADER_KEY,BuildConfig.API_KEY)
                }
                parameter(HttpRoutes.Q_LANGUAGE, "en")
                parameter(HttpRoutes.Q_SORT_BY, "publishedAt")
                parameter(HttpRoutes.Q_PAGE_SIZE, 100)
                parameter(HttpRoutes.Q_PAGE, 1)
                contentType(
                    type = ContentType.Application.Json
                )
                body = NewsDTO
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            println(e.response.status.description)
            null
        } catch (e: ClientRequestException) {
            // 4xx
            println(e.response.status.description)
            null
        } catch (e: ServerResponseException) {
            // 5xx
            println(e.response.status.description)
            null
        } catch (e: Exception) {
            null
        }
    }
}
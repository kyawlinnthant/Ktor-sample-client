package klt.mdy.ktor.data.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import klt.mdy.ktor.data.remote.dto.NewsDTO

interface ApiService {
    suspend fun getTopHeadlines(): NewsDTO
    suspend fun createTopHeadlines(): NewsDTO?

    companion object {
        fun create(): ApiService {
            return NewsRepositoryImpl(
                client = HttpClient(
                    engineFactory = Android
                ) {
                    install(feature = Logging) {
                        level = LogLevel.ALL
                    }
                    install(feature = JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}
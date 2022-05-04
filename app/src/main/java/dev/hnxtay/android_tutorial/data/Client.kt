package dev.hnxtay.android_tutorial.data

import dev.hnxtay.android_tutorial.Response
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

object Client {
    private const val POSTS_URL = "https://api.unsplash.com/search/photos?query=vietnamese-girl"
    private val httpClient = HttpClient(Android) {
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
        install(Auth) {
        }
    }

    suspend fun getPostResponse(): Response {
        return httpClient.get(POSTS_URL) {
            header("Authorization", "Client-ID jamH7RK3C-guibPtVJZke3-UdLGxrQz59H6GGInQhQs")
        }
    }
}

package io.github.loshine.v2compose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun serializer(): JsonSerializer = KotlinxSerializer(Json {
        ignoreUnknownKeys = true
    })

    @Provides
    fun httpClient(
        serializer: JsonSerializer,
    ) = HttpClient(OkHttp) {
        install(JsonFeature) {
            this.serializer = serializer
        }
        engine {
            this.config {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                })
            }
        }
    }
}
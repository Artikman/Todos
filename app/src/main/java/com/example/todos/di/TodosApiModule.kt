package com.example.todos.di

import com.example.todos.data.source.remote.DefaultTodosApi
import com.example.todos.data.source.remote.TodosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TodosApiModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(io.ktor.client.engine.okhttp.OkHttp) {
            defaultRequest {
                headers {
                    append("application/json", "charset=utf-8")
                    append(HttpHeaders.Authorization, "client-ID")
                }
                url {
                    protocol = URLProtocol.HTTPS
                    host = "gorest.co.in"
                }
            }
            install(JsonFeature) {
                GsonSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideTodosApi(
        okHttpClient: HttpClient
    ): TodosApi {
        return DefaultTodosApi(okHttpClient)
    }
}
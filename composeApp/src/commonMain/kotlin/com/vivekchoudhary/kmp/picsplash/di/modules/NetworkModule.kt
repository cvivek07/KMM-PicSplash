package com.vivekchoudhary.kmp.picsplash.di.modules

import com.vivekchoudhary.kmp.picsplash.data.network.ApiService
import com.vivekchoudhary.kmp.picsplash.data.network.ApiServiceImpl
import com.vivekchoudhary.kmp.picsplash.data.network.NetworkConstants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NetworkConstants.BASE_URL
                    parameters.append(
                        NetworkConstants.CLIENT_ID_KEY,
                        NetworkConstants.CLIENT_ID_VALUE
                    )
                }

                header(HttpHeaders.ContentType, ContentType.Application.Json)

            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }
        }
    }
    single<ApiService> { ApiServiceImpl(get()) }
}

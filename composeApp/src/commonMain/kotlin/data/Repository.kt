package data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.encodeURLParameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Repository {

    suspend fun solveWithQueryMethod(query: String): String =
        try {
            val url = "$BACKEND_URL/query-method?query=${
                query.encodeURLParameter()
            }"
            val solution: String = httpClient.get(
                urlString = url,
            ).body()
            println("Got response solution: $solution")
            solution
        } catch (e: Exception) {
            println("Exception - Repository - queryMethodSolution: $e")
            "Error occurred."
        }
}

private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

private const val BACKEND_URL = "http://127.0.0.1:8000/api"

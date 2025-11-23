import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

fun main() = runBlocking {

    // URL DO SUPABASE — PODE SUBIR NO GITHUB SEM PROBLEMA
    val supabaseUrl = "https://mufqbybhmjuopmfcyend.supabase.co"

    // A CHAVE VEM DE VARIÁVEL DE AMBIENTE (SEGURA)
    val supabaseKey = System.getenv("SUPABASE_KEY")
        ?: error("A variável de ambiente SUPABASE_KEY não foi definida!")

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    // Exemplo: Pegar dados da tabela "usuarios"
    val response: String = client.get("$supabaseUrl/rest/v1/usuarios") {
        header("apikey", supabaseKey)
        header("Authorization", "Bearer $supabaseKey")
    }.body()

    println(response)

    client.close()
}

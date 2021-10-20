import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun main() {
    val client = HttpClient(CIO)

    val example1: String = client.get<HttpResponse>("http", "localhost", 8080, "/")
        .receive()
    println("Beispiel 1: $example1")


    val example2: String = client.post<HttpResponse>("http", "localhost", 8080, "/echo", "Echoooooooooooooooo")
        .receive()
    println("Beispiel 2: $example2")

    val example3: String = client.get<HttpResponse>("http", "localhost", 8080, "/items")
        .receive()
    println("Beispiel 3: $example3")

    val example4: String = client.get<HttpResponse>("http", "localhost", 8080, "/items/1")
        .receive()
    println("Beispiel 4: $example4")

    val example5: String = client.get<HttpResponse>("http", "localhost", 8080, "/login?username=Boris&password=password1")
            .receive()
    println("Beispiel 5: $example5")


    try {
        val resp: HttpResponse = client.get("http", "localhost", 8080, "/login")
        println("Beispiel 6: Statuscode ${resp.status}") // wird nicht geprintet, da unauthorized eine exception schmeißt
    } catch (e: Exception) {
        println("Beispiel 6: ${e.message}")
    }

    client.close()
}
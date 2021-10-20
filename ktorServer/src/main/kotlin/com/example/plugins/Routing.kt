package com.example.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        // Beispiel 1
        get("/") {
            call.respondText("Hello World!")
        }

        // Beispiel 2
        post("/echo") {
            val request: String = call.receive()
            println(request)
            call.respondText(request)
        }

        // Beispiel 3
        get("/items") {
            call.respondText(listOf("Beer", "Cola", "Milk").toString())
        }

        // Beispiel 4
        get("/items/{id}") {
            val resp = when (call.parameters["id"]) {
                "1" -> "1. Beer"
                "2" -> "2. Cola"
                "3" -> "3. Milk"
                else -> "false id"
            }
            call.respondText(resp)
        }

        // Beispiel 5 und 6
        get("/login") {
            val queryStrings = call.request.queryParameters
            if ("username" !in queryStrings || "password" !in queryStrings)
                call.response.status(HttpStatusCode.Unauthorized)
            else
                call.respondText("Hello ${queryStrings["username"]}")
        }
    }
}

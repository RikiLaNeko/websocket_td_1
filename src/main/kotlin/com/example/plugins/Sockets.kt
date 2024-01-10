package com.example.plugins

import com.example.Requettes
import com.example.model.Valeur
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import java.time.Duration

fun Application.configureSockets() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
        pingPeriod = Duration.ofSeconds(60)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    val requettes = Requettes()
    routing {
        webSocket("/ws/valeur/{id}" ) {

            var oldValeur: Valeur? = null
            while (true){
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    send(Frame.Text("Invalid id"))
                    return@webSocket
                }
                val valeur:Valeur? = requettes.getValeur(id)
                if (valeur==null){
                    send(Frame.Text("Invalid id"))
                    return@webSocket
                }else{
                    if (valeur != oldValeur){
                        sendSerialized(valeur)
                        oldValeur=valeur
                    }
                }
                delay(1000)
            }
        }
    }
}

package org.example.controllers.step2

import org.springframework.stereotype.Component

@Component
class WorkQueueConsumer {

    fun workQueueTask(message: String) {

        val messageSplit = message.split("|")
        val originalMessage = messageSplit[0]
        val duration = messageSplit[1].toLong()

        println("[#] received: $originalMessage (duration: $duration ms)")

        try {
            val seconds = duration / 1000
            for (i in 1 .. seconds) {
                Thread.sleep(1000)
                print(".")
            }
        }
        catch(e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
        println()
        println("[#] completed: $originalMessage")
    }
}
package com.example

import javafx.concurrent.Service
import javafx.concurrent.Task

class SampleService: Service<String>() {
    override fun createTask(): Task<String> {
        return SampleTask()
    }

    private class SampleTask: Task<String>() {
        override fun call(): String {
            (0L..1000L).forEach { i ->
                updateProgress(i, 1000L)
                Thread.sleep(10L)
            }
            return "** RESULT **"
        }
    }
}
package com.example

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage?) {
        primaryStage ?: return
        val root = FXMLLoader.load<BorderPane>(javaClass.getResource("Main.fxml"))
        val scene = Scene(root)
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}
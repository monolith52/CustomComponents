package com.example

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.HBox

class MainController {
    @FXML private lateinit var button: Button
    @FXML private lateinit var statusBar: HBox
//    val serviceStatus = ServiceStatus()
    @FXML private lateinit var serviceStatus: ServiceStatus
    private val service = SampleService()

    @FXML fun initialize() {
//        statusBar.children += serviceStatus
        serviceStatus.service = service
    }

    @FXML fun onButtonAction() {
        service.restart()
    }
}
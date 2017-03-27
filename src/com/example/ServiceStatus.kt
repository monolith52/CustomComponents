package com.example

import javafx.animation.Animation
import javafx.animation.Interpolator
import javafx.animation.RotateTransition
import javafx.concurrent.Service
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.HBox
import javafx.scene.shape.SVGPath
import javafx.scene.text.Text
import javafx.util.Duration

class ServiceStatus : HBox() {

    @FXML private lateinit var ban: SVGPath
    @FXML private lateinit var running: SVGPath
    @FXML private lateinit var label: Text
    private var animation: RotateTransition? = null
    fun setText(value: String) { label.text = value }
    fun getText(): String { return label.text }
    var text2: String = "hello"
    var service: Service<*>? = null
        set(value) {
            value ?: return
            running.visibleProperty().bind(value.runningProperty())
            ban.visibleProperty().bind(value.runningProperty().not())
            value.runningProperty().addListener { _, _, current ->
                toggleAnimation(current)
            }
        }

    init {
        val loader = FXMLLoader(javaClass.getResource("ServiceStatus.fxml"))
        loader.setRoot(this)
        loader.setController(this)
        loader.load<HBox>()
    }

    @FXML private fun initialize() {
        running.isVisible = false
        animation = RotateTransition(Duration.millis(1000.0), running)
        animation?.fromAngle = 0.0
        animation?.toAngle = 360.0
        animation?.cycleCount = 10000
        animation?.interpolator = Interpolator.LINEAR
    }

    fun toggleAnimation(state: Boolean) {
        if (state and Animation.Status.STOPPED.equals(animation?.status)) animation?.playFromStart()
        else if (!state and !Animation.Status.STOPPED.equals(animation?.status)) animation?.stop()
    }
}
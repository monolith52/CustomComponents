package com.example;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class ServiceStatus2 extends HBox {

    @FXML private SVGPath ban;
    @FXML private SVGPath running;
    @FXML private Text label;
    private RotateTransition animation = null;

    private String text = null;
    public void setText(String value) {
        label.setText(value);
    }
    public String getText() {
        return label.getText();
    }

    private Service<?> service = null;

    public ServiceStatus2() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ServiceStatus.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void initialize() {
        running.setVisible(false);
        animation = new RotateTransition(Duration.millis(1000.0), running);
        animation.setFromAngle(0.0d);
        animation.setToAngle(360.0d);
        animation.setCycleCount(10000);
        animation.setInterpolator(Interpolator.LINEAR);
    }

    public void setService(Service<?> value) {
        this.service = value;
        running.visibleProperty().bind(value.runningProperty());
        ban.visibleProperty().bind(value.runningProperty().not());
        value.runningProperty().addListener( (prop, old, current) ->
                toggleAnimation(current)
        );
    }

    public Service<?> getService() { return service; }

    private void toggleAnimation(Boolean state) {
        if (state && Animation.Status.STOPPED.equals(animation.getStatus())) animation.playFromStart();
        if (!state && !Animation.Status.STOPPED.equals(animation.getStatus())) animation.stop();
    }
}
package com.myproject.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;

public class PetApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane=new Pane();
        Rectangle2D bounds=Screen.getPrimary().getBounds();
        double screenWidth=bounds.getWidth();
        double screenHeight=bounds.getHeight();
        Image image=new Image(new FileInputStream("javafx/src/main/resources/com/myproject/javafx/灵儿.png"));
        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(200);
        imageView.setLayoutX(screenWidth-200);
        imageView.setLayoutY(screenHeight-330);
        pane.getChildren().add(imageView);
        pane.setMaxWidth(screenWidth/2);
        pane.setMaxHeight(screenHeight/2);
        Scene scene = new Scene(pane, screenWidth, screenHeight);
        scene.setFill(Color.TRANSPARENT);
        pane.setStyle("-fx-background-color: rgba(255,255,255,0);");
        stage.setScene(scene); // Place the scene in the stage
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setAlwaysOnTop(true);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,new PetEvent());
    }

    public static void main(String[] args) {
        launch();
    }
}
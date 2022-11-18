package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        VBox group=new VBox();
        group.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Text text=new Text(100,100,"wc");
        Rectangle rectangle=new Rectangle(100,100,100,100);
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.BLACK);
        group.getChildren().add(text);
        group.getChildren().add(rectangle);
        Scene scene1=new Scene(group,400,400);
        TextField textField=new TextField();
        stage.setTitle(String.valueOf(screenRectangle.getHeight())+" "+String.valueOf(screenRectangle.getWidth()));
//        Image image =new Image("a.jpg");
//        ImageView imageView=new ImageView(image);
//        group.getChildren().add(imageView);
        stage.setScene(scene1);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
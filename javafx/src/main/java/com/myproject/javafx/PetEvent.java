package com.myproject.javafx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

/**
 * @author lkxl
 */
public class PetEvent implements EventHandler<MouseEvent> {


    @Override
    public void handle(MouseEvent mouseEvent) {
       MouseButton button=mouseEvent.getButton();
       if(mouseEvent.isPrimaryButtonDown()){

       }else if(mouseEvent.isSecondaryButtonDown()){
           Popup popup=new Popup();
           Label label=new Label("测试");
           label.setStyle(" -fx-background-color:white;");
           label.addEventHandler(MouseEvent.MOUSE_CLICKED,new LabelHandler());
       }
    }
}

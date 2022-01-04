package com.example.lesson4_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloController {

    @FXML private TextField textField;
    @FXML private Button sendButton;
    @FXML private TextArea textArea;
    @FXML public ListView<String> userList;

    public void sendMessage() {

        if(!textField.getText().isEmpty()){
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
            textArea.appendText(String.format("%s: %s", localDateTime.format(formatter), textField.getText()));
            textArea.appendText(System.lineSeparator());
            textField.clear();
            textField.setFocusTraversable(true);
        }
    }
}
module com.example.lesson4_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson4_javafx to javafx.fxml;
    exports com.example.lesson4_javafx;
}
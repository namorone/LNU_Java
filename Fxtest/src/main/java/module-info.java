module com.example.fxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.fxtest to javafx.fxml;
    exports com.example.fxtest;
}
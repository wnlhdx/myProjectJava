module com.myproject.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.myproject.javafx to javafx.fxml;
    exports com.myproject.javafx;
}
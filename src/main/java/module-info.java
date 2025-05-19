module org.example.whowantstobeamillionaire {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens org.example.whowantstobeamillionaire to javafx.fxml;
    exports org.example.whowantstobeamillionaire;
}
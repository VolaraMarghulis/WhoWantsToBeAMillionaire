module org.example.whowantstobeamillionaire {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.whowantstobeamillionaire to javafx.fxml;
    exports org.example.whowantstobeamillionaire;
}
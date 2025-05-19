module org.example.whowantstobeamillionaire {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens org.example.whowantstobeamillionaire to javafx.fxml;
    opens org.example.whowantstobeamillionaire.functional;

    exports org.example.whowantstobeamillionaire;
    exports org.example.whowantstobeamillionaire.functional;
}
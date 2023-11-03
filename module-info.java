module com.example.wheatherapp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.json;


    opens com.example.wheatherapp1 to javafx.fxml;
    exports com.example.wheatherapp1;
}
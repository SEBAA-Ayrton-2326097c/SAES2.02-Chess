module com.chessapp.chessapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.chessapp.chessapp to javafx.fxml;
    exports com.chessapp.chessapp;
}
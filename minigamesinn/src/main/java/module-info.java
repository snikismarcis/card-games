module minigames {
    requires javafx.controls;
    requires javafx.fxml;

    opens minigames to javafx.fxml;
    exports minigames;
}

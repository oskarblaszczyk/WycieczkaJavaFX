module kurs.wycieczkajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens kurs.wycieczkajavafx to javafx.fxml;
    exports kurs.wycieczkajavafx;
    exports kurs.wycieczkajavafx.controllers;
    opens kurs.wycieczkajavafx.controllers to javafx.fxml;
}
package fr.insa.forgeard.interfacegraphique;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("editeur");
    }
    public void dessinerPoint() throws IOException {
        App.setRoot("coordonnees");
    }
    
    }


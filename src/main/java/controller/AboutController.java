package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author phucnn
 */
public class AboutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Label contact;
    public Button btnChange;

    public void changeColor(ActionEvent actionEvent) {
        contact.setText("Ahihi");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekopdracht;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Max
 */
public class FXMLDocumentController implements Initializable {

    private Search search = new Search();
    @FXML
    private Button btnOpen;
    @FXML
    private Button btnBrowse;
    @FXML
    private TextField txtDir;
    @FXML
    private Button btnSearch;
    @FXML
    private ListView lvResults;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtParameter;

    @FXML
    private void openFolderDialog() {
        DirectoryChooser chooseDir = new DirectoryChooser();
        String result = chooseDir.showDialog(btnBrowse.getScene().getWindow()).toString();
        txtDir.setText(result);
    }

    @FXML
    private void openFileDialog() {

    }

    @FXML
    private void btnSearch() {
        try {
            if (search.clearResults()) {
                search.findResults(txtDir.getText(), txtParameter.getText());
                lvResults.setItems(search.getResults());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Search parameter required!");
                alert.setContentText("Please enter a search query.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void saveToFile() throws IOException {
        String content = "You searched for " + txtParameter.getText() + " which resulted in" + search.getResults().size() + " results." + '\n';

        for (Object s : search.getResults()) {
            content += s.toString() + '\n';
        }

        File file = new File("SearchResults.rtf");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

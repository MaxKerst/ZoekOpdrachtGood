/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekopdracht;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private void openSelectedResult() {
        try {
            if (lvResults.getSelectionModel().getSelectedItem() != null) {
                Desktop.getDesktop().open((File) lvResults.getSelectionModel().getSelectedItem());
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openFolderDialog() {
        DirectoryChooser chooseDir = new DirectoryChooser();
        String result = chooseDir.showDialog(btnBrowse.getScene().getWindow()).toString();
        txtDir.setText(result);
    }

    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }

    @FXML
    private void btnSearch() {
        if (!isNullOrBlank(txtParameter.getText())) {
            if (!isNullOrBlank(txtDir.getText())) {
                try {
                    if (search.clearResults()) {
                        search.findResults(txtDir.getText(), txtParameter.getText());
                        if (search.getResults().size() > 0) {
                            lvResults.setItems(search.getResults());
                        } else {
                            Alert noresults = new Alert(AlertType.INFORMATION);
                            noresults.setTitle("Notification");
                            noresults.setHeaderText("No results!");
                            noresults.setContentText("Please try another search query.");
                            noresults.showAndWait();
                        }
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
            } else {
                Alert errormsgdir = new Alert(AlertType.ERROR);
                errormsgdir.setTitle("Error");
                errormsgdir.setHeaderText("No directory selected!");
                errormsgdir.setContentText("Please enter a directory to search in.");
                errormsgdir.showAndWait();
            }
        } else {
            Alert errormsgparam = new Alert(AlertType.ERROR);
            errormsgparam.setTitle("Error");
            errormsgparam.setHeaderText("Search parameter required!");
            errormsgparam.setContentText("Please enter a search query.");
            errormsgparam.showAndWait();
        }
    }

    @FXML
    private void saveToFile() throws IOException {
        java.util.Date date = new java.util.Date();
        String content = date.toString() + '\n' + "You searched for '" + txtParameter.getText() + "' which resulted in " + search.getResults().size() + " results." + '\n' + '\n';

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

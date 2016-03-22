/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekopdracht;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Max
 */
public class Search {

    private ObservableList<File> _results;

    public Search() {
        _results = FXCollections.observableArrayList();
    }

    public boolean clearResults() {
        try {
            _results.clear();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public ObservableList<File> getResults() {
        return _results;
    }

    public void findResults(String directoryLocation, String parameter) {
        File file = new File(directoryLocation);
        File[] dir = file.listFiles();
        if (dir != null) {
            for (File f : dir) {
                if (f != null) {
                    if (f.isDirectory() && !f.isHidden()) {
                        if (f.getName().toLowerCase().contains(parameter.toLowerCase())) {
                            _results.add(f);
                        }
                        findResults(f.getPath(), parameter);
                    } else if (f.getName().toLowerCase().contains(parameter.toLowerCase()) && !f.isHidden()) {
                        _results.add(f);
                    }
                }
            }
        }
    }
}

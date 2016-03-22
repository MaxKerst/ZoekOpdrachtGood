/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekopdracht;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Max
 */
public class SearchTest {

    private Search search;

    public SearchTest() {
        /**
         * Creation of a Search-object. Search doesn't have any parameters.
         */
        //public Search()
    }

    @Test
    public void testFindResults() {
        /**
         *
         * @param directoryLocation the path from where the search will start
         * @param parameter the group of characters that will be searched for in
         * directory names and file names
         *
         * @return void
         */
        //Search findResults(String directoryLocation, String parameter);
    }

    /**
     * Set-up of the unit test class Creating an instance of the Search class
     */
    @Before
    public void setUp() {
        search = new Search();
    }

    /**
     * Test of the FindResults method, in the class Search.
     */
    @Test
    public void testFindResultsSuccesfully() {
        // @param findResults(directory, parameter)
        FileSystemView filesys = FileSystemView.getFileSystemView();
        File[] roots = filesys.getRoots();
        Search search = new Search();
        search.findResults(filesys.getHomeDirectory().getAbsolutePath(), "test");
    }
}

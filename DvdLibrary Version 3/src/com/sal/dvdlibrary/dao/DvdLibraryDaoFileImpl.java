/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.dao;

import com.sal.dvdlibrary.dto.DvD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author vicmaia
 */
public class DvdLibraryDaoFileImpl implements dvdLibraryDao {

    //declare variables
    public final String DVD_FILE;
    public static final String DELIMITER = "::";
    /*
    Hash Map to store and retrieve the dvd with title name
     */
    private Map<String, DvD> dvds = new HashMap<>();

    //constructor that takes the dvdTest file

    public DvdLibraryDaoFileImpl(){ //no arg constructor typically used
        DVD_FILE = "dvdTest.txt";
    }
    public DvdLibraryDaoFileImpl(String libraryTextFile){
        DVD_FILE = libraryTextFile;
    }


    //method that adds dvd into dvdTest.txt file

    @Override
    public DvD addDvd(String title, DvD dvd) throws DvdLibraryDaoException{

        loadDvdFile();
        DvD prevDvd = dvds.put(title, dvd);
        writeDvdFile();
        return prevDvd;

    }

    /*
    This code gets all of the DvDs objects out of the dvds map as a collection by calling the values() method.
     */
    @Override
    public List<DvD> getAllDvds() throws DvdLibraryDaoException {

        loadDvdFile();
        return new ArrayList<DvD>(dvds.values());
    }

    /*
    This method is quite simple — we just ask the dvds map for the dvd object with the given title and return it
     */
    @Override
    public DvD getDvd(String title)throws DvdLibraryDaoException {

        loadDvdFile();
        return dvds.get(title);
    }

    //method that adds dvd into dvdTest.txt file
    @Override
    public DvD removeDvd(String title) throws DvdLibraryDaoException{

        loadDvdFile();
        DvD removedDvd = dvds.remove(title);
        writeDvdFile();
        return removedDvd;
    }



    /*
    Method to unmarshall the object or read a line of string
    from the line and convert it into an object
     */
    private DvD unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvdTitle is in index 0 of the array.
        String dvdTitle = dvdTokens[0];

        // Which we can then use to create a new DvD object to satisfy
        // the requirements of the DvD constructor.
        DvD dvdFromFile = new DvD(dvdTitle);

        // However, there are 5 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - setReleaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - setMPAA
        dvdFromFile.setMPAA(dvdTokens[2]);

        // Index 3 - setDirectorsName
        dvdFromFile.setDirectorsName(dvdTokens[3]);

        // Index 4 - setStudio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - setUserRating
        dvdFromFile.setUserRating(dvdTokens[5]);

        return dvdFromFile;
    }

    /*
    Method to Load file DVD_FILE into memory
     */
    private void loadDvdFile() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent student unmarshalled
        DvD currentDvd;
        // Go through DVD_FILE line by line, decoding each line into a
        // DvD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DvD
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the dvd as the map key for our dvd object.
            // Put currentDvd into the map using student id as the key
            dvds.put(currentDvd.getDvD(), currentDvd);
        }
        // close scanner
        scanner.close();
    }


    private String marshallDvd(DvD aDvd) {
        // We need to turn a DvD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        String dvdAsText = aDvd.getDvD() + DELIMITER;

        // add the rest of the properties in the correct order:

        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        dvdAsText += aDvd.getMPAA() + DELIMITER;

        dvdAsText += aDvd.getDirectorsName() + DELIMITER;

        dvdAsText += aDvd.getStudio() + DELIMITER;

        //getUserRating does not need DELIMITER.

        dvdAsText += aDvd.getUserRating();

        return dvdAsText;
    }

    /**
     * Writes all Dvds in the library out to a DVD_FILE. See loadDvdFile
     * for file format.
     *
     * @throws Exception if an error occurs writing to the file
     */
    private void writeDvdFile() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.

        // Write out the DvD objects to the DVD file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvd and iterate over them but we've
        // already created a method that gets a List of dvds so
        // we'll reuse it.

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }

        // Write out the DvD objects to the dvd file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        String dvdAsText;
        List<DvD> dvdList = this.getAllDvds();
        for (DvD currentDvd : dvdList) {
            // turn a DvD into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the DvD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();


    }

    /* Each of the following methods edit information when user chooses an option/
    For example: when user chooses to edit a dvd, the second menu will ask the user what they want to change
    in dvd. If, for example, user chooses release date, the program will ask them to change the date,
    and the method will take the dvd title from the list and change the information requested by the user.
     */
    @Override
    public DvD editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DvD editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DvD editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setDirectorsName(newDirectorName);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DvD editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DvD editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        writeDvdFile();
        return currentDVD;
    }

}

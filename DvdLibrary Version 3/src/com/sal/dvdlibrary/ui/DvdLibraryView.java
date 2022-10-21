/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.ui;

import com.sal.dvdlibrary.dto.DvD;
import java.util.List;

/**
 *
 * @author vicmaia
 */
public class DvdLibraryView {

    //declare io member field.

    private UserIO io;

    //a constructor that initializes the io member field.

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    //Main menu. User selects an option to go the next menu.

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1 - List All DVDs");
        io.print("2 - Add New DVDs");
        io.print("3 - View DVD");
        io.print("4 - Remove DVD");
        io.print("5 - Edit a  DVD");
        io.print("6 - Exit");

        return io.readInt("Please select from the menu", 1,6);
    }

    //Edit menu.
    public int printEditMenuAndGetSelection() {
        io.print("Which option do you change to Edit?");
        io.print("1 - Release Date");
        io.print("2 - MPAA rating");
        io.print("3 - Director's name");
        io.print("4 - Studio");
        io.print("5 - Comment");

        return io.readInt("Please select from the menu", 1,5);
    }
    /*
     This method prompts the user for title, releaseDate, MPAA rating, directorsName,studio, and userRating,
    gathers this information, creates a new dvd object, and returns it to the caller.
     */
    public DvD getNewDvDInfo() {
        String dvdTitle = io.readString("Please enter new DVD title");
        String releaseDate = io.readString("Please enter Release Date");
        String MPAA = io.readString("Please enter MPAA rating");
        String directorsName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio");
        String userRating = io.readString("Please enter a comment about the movie");

        DvD currentDvd = new DvD(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMPAA(MPAA);
        currentDvd.setDirectorsName(directorsName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    /*
    This method simply displays a banner or heading to the UI indicating that
    the next interactions on the screen will be for creating a new dvd
     */
    public void displayCreateDvDBanner() {
        io.print("=== Create DvD ===");
    }

    /*
    The second method displays a message that the new dvd was successfully created
    and waits for the user to hit Enter to continue
     */
    public void displayCreateSuccessBanner() {
        io.readString(
                "DvD successfully created.  Please hit enter to continue");
    }

    /*
    A method that takes a list of DVD objects as a parameter and displays the information for each Dvd to the screen.
     */
    public void displayDvdList(List<DvD> dvdList) {
        for (DvD currentDvd : dvdList) {
            String dvdInfo = String.format("#%s : %s %s",
                    currentDvd.getDvD(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMPAA(),
                    currentDvd.getDirectorsName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    //banner that displays all dvds
    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    /*
    Shows the dvd banner
     */
    public void displayDisplayDvdBanner() {
        io.print("=== Display Dvd ===");
    }

    /*
    Get the dvd title to display information
     */
    public String getDvdTitleChoice() {
        return io.readString("Please enter the dvd title.");
    }

    /*
    Displays the dvd information
     */
    public void displayDvd(DvD dvd) {
        if (dvd != null) {
            io.print(dvd.getDvD());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAA());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    //banner that shows removeDVD menu
    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }

    /*
    If DVD title exists on the list, it will be removed after removeDvd  is called
    and display the message that the dvd was removed.
    Otherwise, it will display a message that the there is no such DVD.
     */
    public void displayRemoveResult(DvD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    //message that is shown after user chooses to exit the program
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    //handles unknown commands and exiting.
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    //display error message when data is not saved into memory
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    //display edit banner when user chooses to edit DVD
    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    //after editDvd() is called, the program will show a message saying the DVD was edited.
    public void displayEditDvdSuccess() {
        io.readString(
                "DVD successfully Edited.  Please hit enter to continue");
    }

    //each of the following methods shows a banner when user selects a option

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
    }

    //each of the following methods asks the user to input new information to edit
    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    //when DVD is not in the list, the message appears
    public void displayNullDVD() {
        io.print("No such DVD.");
        io.readString("Please hit enter to continue.");
    }
}

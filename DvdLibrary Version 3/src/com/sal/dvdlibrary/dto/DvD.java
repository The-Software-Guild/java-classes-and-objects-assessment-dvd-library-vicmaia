/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.dto;

/**
 *
 * @author vicmaia
 */
import java.util.Date;

public class DvD {

    //declare variables
    private String title;
    private String ReleaseDate;
    private String MPAA;
    private String DirectorsName;
    private String Studio;
    private String UserRating;


    //contructor

    public DvD(String title) {
        this.title = title;
    }


    //Each of the following methods takes a parameter and assigns it to the attribute..
    public void setReleaseDate(String ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    public void setDvD(String title) {
        this.title = title;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public void setDirectorsName(String DirectorsName) {
        this.DirectorsName = DirectorsName;
    }


    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    public void setUserRating(String UserRating) {
        this.UserRating = UserRating;
    }

    //Each of the following methods returns the value of the attribute.
    public String getReleaseDate() {
        return ReleaseDate;
    }


    public String getDvD() {
        return title;
    }


    public String getMPAA() {
        return MPAA;
    }

    public String getDirectorsName() {
        return DirectorsName;
    }

    public String getStudio() {
        return Studio;
    }


    public String getUserRating() {
        return UserRating;
    }
}

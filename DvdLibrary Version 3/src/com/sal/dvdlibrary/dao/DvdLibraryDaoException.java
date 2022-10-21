/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.dao;

/**
 *
 * @author vicmaia
 */


public class DvdLibraryDaoException extends Exception{

    /*
     * the first constructor in cases where something is wrong in our application
     * but it isn't caused by another exception
     */
    public DvdLibraryDaoException(String message) {
        super(message);
    }
    /*
     * the second constructor in cases where something is wrong in
     * our application that is caused by another exception in the underlying implementation
     */
    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}

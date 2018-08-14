package com.TeamAA.Beer_Journal.Exceptions;

/**
 * Exception class for when a DAO cannot find any data for a given query
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 8:51 PM
 */
public class NoDataFoundException extends RuntimeException {


    public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}

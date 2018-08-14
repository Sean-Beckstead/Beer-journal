package com.TeamAA.Beer_Journal.Exceptions;

/**
 * Exception class for when a DAO cannot find a row for a given primary key
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 8:48 PM
 */
public class RowNotFoundException extends RuntimeException {

    public RowNotFoundException() {
        super();
    }

    public RowNotFoundException(String message) {
        super(message);
    }
}

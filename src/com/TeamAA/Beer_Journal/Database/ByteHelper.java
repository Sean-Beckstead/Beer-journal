package com.TeamAA.Beer_Journal.Database;

/**
 * This class provides some utility methods to convert between hexadecimal strings and byte arrays.
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 10:28 AM
 */
public class ByteHelper {
    /**
     * The array of valid hexadecimal characters
     */
    final protected static char[] hexArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * Convert a byte array to a hexadecimal string
     *
     * @param bytes the byte array to convert
     * @return the hexadecimal string representation of the byte array
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Convert a hexadecimal string to a byte array
     *
     * @param s The hexadecimal string to convert
     * @return The byte array representation of the hexadecimal string
     */
    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}

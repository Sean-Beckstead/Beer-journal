package com.TeamAA.Beer_Journal.Database.DTO;

/**
 * This class represents an actual property of a Journal Entry
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 9:08 PM
 */
public class EntryPropertyDTO {

    /**
     * Id should never be assigned manually, it will get assigned by the dao when it's saved
     */
    private int id;
    private int journalEntryId;
    private String propertyName;
    private byte[] value;
    private String propertyType;

    /**
     * This constructor should be used when building new data
     */
    public EntryPropertyDTO() {
    }

    ;

    /**
     * This constructor should be used when building new data
     *
     * @param journalEntryId The id of the JournalEntryDTO that this EntryPropertyDTO belongs to
     * @param propertyName   The name of the property
     * @param propertyType   The type of the property
     * @param value          The value of the property
     */
    public EntryPropertyDTO(int journalEntryId, String propertyName, String propertyType, byte[] value) {
        this.id = id;
        this.journalEntryId = journalEntryId;
        this.propertyName = propertyName;
        this.value = value;
        this.propertyType = propertyType;
    }

    /**
     * This constructor should be used by the DAO to return an already existing item
     *
     * @param id             An automatically assigned id. This should never be set manually
     * @param journalEntryId The id of the JournalEntryDTO that this EntryPropertyDTO belongs to
     * @param propertyName   The name of the property
     * @param propertyType   The type of the property
     * @param value          The value of the property
     */
    public EntryPropertyDTO(int id, int journalEntryId, String propertyName, String propertyType, byte[] value) {
        this.id = id;
        this.journalEntryId = journalEntryId;
        this.propertyName = propertyName;
        this.value = value;
        this.propertyType = propertyType;
    }

    public int getId() {
        return id;
    }

    public int getJournalEntryId() {
        return journalEntryId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setJournalEntryId(int journalEntryId) {
        this.journalEntryId = journalEntryId;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}

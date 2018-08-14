package com.TeamAA.Beer_Journal.Database.DAO;

import com.TeamAA.Beer_Journal.Database.DTO.EntryPropertyDTO;
import com.TeamAA.Beer_Journal.Database.DTO.JournalEntryDTO;
import com.TeamAA.Beer_Journal.Exceptions.NoDataFoundException;

import java.util.HashMap;
import java.util.List;

/**
 * This class provides a way to interact with the entry_properties table
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 8:58 PM
 */
public interface EntryPropertyDAO {
    /**
     * The name of the table to store entryProperties
     */
    public static final String ENTRY_PROPERTIES_TABLE = "entry_properties";
    /**
     * The id column
     */
    public static final String ID = "id";
    /**
     * The the journalEntryId column
     */
    public static final String JOURNAL_ENTRY_ID = "entry_id";
    /**
     * The propertyName column
     */
    public static final String PROPERTY_NAME = "name";
    /**
     * The propertyType column
     */
    public static final String PROPERTY_TYPE = "type";

    /**
     * The value column
     */
    public static final String VALUE = "value";

    /**
     * Get all of the properties associated with a given journalEntry
     *
     * @param journalEntry The journalEntry to get the properties for
     * @return The properties associated with the journalEntry
     * @throws NoDataFoundException This error tells you there was no data found for the given journalEntry
     */
    public HashMap<String, EntryPropertyDTO> getEntryProperties(JournalEntryDTO journalEntry) throws NoDataFoundException;

    /**
     * Look up all of the journalEntries by the value of a specific property
     *
     * @param propertyName The name of the property to search by
     * @param value        the value of the property to find
     * @return The list of journalEntries that have the property with the given value
     */
    public List<JournalEntryDTO> getJournalEntriesByPropValue(String propertyName, byte[] value);

    /**
     * Update the record if it exists, otherwise insert the record.
     *
     * @param property The property to save or update
     * @return The property after being inserted or updated. If it was inserted, it will now have an id associated with it.
     */
    public EntryPropertyDTO saveOrUpdate(EntryPropertyDTO property);

    /**
     * This method removes all of the properties for a given journalEntry
     *
     * @param journalEntry The journalEntry to remove the properties of
     * @return The properties that were removed
     */
    public HashMap<String, EntryPropertyDTO> removeAllProperties(JournalEntryDTO journalEntry);

    /**
     * Save or update all properties in the given hashmap. This uses {@link #saveOrUpdate(com.TeamAA.Beer_Journal.Database.DTO.EntryPropertyDTO)} to do the work
     *
     * @param properties The properties to save or update
     * @return The hashmap after being saved or updated
     */
    public HashMap<String, EntryPropertyDTO> saveOrUpdateAll(HashMap<String, EntryPropertyDTO> properties);

    /**
     * Get all of the entry properties stored in the database.
     *
     * @return
     */
    public List<EntryPropertyDTO> getAll();
}

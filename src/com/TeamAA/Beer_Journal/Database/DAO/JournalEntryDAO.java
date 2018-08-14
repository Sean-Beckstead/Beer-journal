package com.TeamAA.Beer_Journal.Database.DAO;

import com.TeamAA.Beer_Journal.Database.DTO.JournalEntryDTO;
import com.TeamAA.Beer_Journal.Exceptions.NoDataFoundException;
import com.TeamAA.Beer_Journal.Exceptions.RowNotFoundException;

import java.util.List;

/**
 * This class provides a way to interact with the journal_entry table.
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 8:57 PM
 */
public interface JournalEntryDAO {
    /**
     * The name of the journalEntry table
     */
    public static final String JOURNAL_ENTRY_TABLE = "journal_entry";
    /**
     * The name of the id column
     */
    public static final String ID = "id";

    /**
     * Get a journalEntry by it's id
     *
     * @param id the id of the journalEntry
     * @return The found journalEntry
     * @throws RowNotFoundException This gets thrown if the journalEntry was not found in the database
     */
    public JournalEntryDTO getJournalEntryById(int id) throws RowNotFoundException;

    /**
     * This gets a random journalEntry out of the database
     *
     * @return
     */
    public JournalEntryDTO getRandomJournalEntry();

    /**
     * Delete a journalEntry and all of its entryProperties
     *
     * @param journalEntry the journalEntry to delete
     * @return the deleted journalEntry
     */
    public JournalEntryDTO removeJournalEntry(JournalEntryDTO journalEntry);

    /**
     * Delete a journalEntry and all of its entryProperties by the id
     *
     * @param id the id to remove
     * @return the deleted journalEntry
     */
    public JournalEntryDTO removeJournalEntryById(int id);

    /**
     * Find journalEntries by using a sql statement
     *
     * @param query       The sql query to run
     * @param queryParams The parameters to set in the query
     * @return The list of journalEntries found
     */
    public List<JournalEntryDTO> findJournalEntry(String query, String[] queryParams) throws NoDataFoundException;

    /**
     * Update all of the entry's properties if it exists,
     * otherwise insert the journalEntry and all of it's properties.
     * <p/>
     * Being that the journalEntry only consists of a primary key, there's never anything other than the properties
     * that will be updated
     *
     * @param journalEntry The journalEntry to insert or update
     * @return The journalEntry after being inserted or updated
     */
    public JournalEntryDTO saveOrUpdate(JournalEntryDTO journalEntry);

    /**
     * Get all of the journalEntries in the database
     *
     * @return The list of all of the journalEntries
     */
    public List<JournalEntryDTO> getAllJournalEntries();
}

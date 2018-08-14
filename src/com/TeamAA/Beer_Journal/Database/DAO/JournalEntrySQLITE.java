package com.TeamAA.Beer_Journal.Database.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.TeamAA.Beer_Journal.Database.DTO.JournalEntryDTO;
import com.TeamAA.Beer_Journal.Exceptions.NoDataFoundException;
import com.TeamAA.Beer_Journal.Exceptions.RowNotFoundException;
import com.TeamAA.Beer_Journal.JournalController;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a SQLite implementation of the JournalEntryDAO.
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 9:01 PM
 */
public class JournalEntrySQLITE implements JournalEntryDAO {
    /**
     * The create table statement for the journalEntry table
     */
    public static final String CREATE_JOURNAL_ENTRY_TABLE = "create table " + JOURNAL_ENTRY_TABLE + " (id integer pirmary key autoincrement);";

    /**
     * The dbHelper is used to access the sqlite database
     */
    private SQLiteDatabase db;

    public JournalEntrySQLITE(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public JournalEntryDTO getJournalEntryById(int id) throws RowNotFoundException {
        JournalEntryDTO journalEntry;
        Cursor cursor = db.query(JOURNAL_ENTRY_TABLE, new String[]{"id"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            journalEntry = buildDTO(cursor);
        } else {
            throw new RowNotFoundException("Could not find ID:" + id);
        }

        if (cursor != null)
            cursor.close();

        return journalEntry;
    }

    @Override
    public JournalEntryDTO getRandomJournalEntry() {
        JournalEntryDTO randomEntry = null;
        Cursor cursor = db.rawQuery("select id from " + JOURNAL_ENTRY_TABLE + " order by random() limit 1;", null);
        if (cursor != null && cursor.getCount() > 0)
            randomEntry = buildDTO(cursor);

        if (cursor != null)
            cursor.close();
        return randomEntry;
    }

    @Override
    public JournalEntryDTO removeJournalEntry(JournalEntryDTO journalEntry) {
        EntryPropertyDAO entryPropertyDAO = JournalController.getEntryPropertyDAO();
        //delete all of the properties first
        entryPropertyDAO.removeAllProperties(journalEntry);
        //delete the journalEntry
        db.delete(JOURNAL_ENTRY_TABLE, "id=?", new String[]{String.valueOf(journalEntry.getId())});
        return journalEntry;
    }

    @Override
    public JournalEntryDTO removeJournalEntryById(int id) {
        JournalEntryDTO journalEntry = getJournalEntryById(id);
        removeJournalEntry(journalEntry);
        return journalEntry;
    }

    @Override
    public List<JournalEntryDTO> findJournalEntry(String query, String[] queryParams) throws NoDataFoundException {
        List<JournalEntryDTO> foundJournalEntries = new ArrayList<JournalEntryDTO>();
        Cursor cursor = db.rawQuery(query, queryParams);
        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                foundJournalEntries.add(buildDTO(cursor));
            }
        } else {
            throw new NoDataFoundException("Could not find any rows for query:" + query + " and queryParams" + queryParams.toString());
        }
        return null;
    }

    @Override
    public JournalEntryDTO saveOrUpdate(JournalEntryDTO journalEntry) {
        JournalEntryDTO newJournalEntry = null;
        Cursor cursor = db.query(JOURNAL_ENTRY_TABLE, new String[]{ID}, "id=?", new String[]{String.valueOf(journalEntry.getId())}, null, null, null);
        EntryPropertyDAO entryPropertyDAO = JournalController.getEntryPropertyDAO();

        if (cursor != null && cursor.getCount() > 0) {
            //update the properties of the journal entry
            entryPropertyDAO.saveOrUpdateAll(journalEntry.getProperties());
        } else {
            //insert the new journal entry and all of its properties
            long rowId = db.insert(JOURNAL_ENTRY_TABLE, "id", null);
            entryPropertyDAO.saveOrUpdateAll(journalEntry.getProperties());
            cursor = db.query(JOURNAL_ENTRY_TABLE, new String[]{ID}, "rowid=?", new String[]{String.valueOf(rowId)}, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                newJournalEntry = buildDTO(cursor);
            }
        }

        if (cursor != null)
            cursor.close();

        return newJournalEntry;
    }

    @Override
    public List<JournalEntryDTO> getAllJournalEntries() {
        List<JournalEntryDTO> journalEntries = new ArrayList<JournalEntryDTO>();
        Cursor cursor = db.query(JOURNAL_ENTRY_TABLE, new String[]{ID}, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                journalEntries.add(buildDTO(cursor));
            }
        } else {
            throw new NoDataFoundException("No Journal Entries were found!");
        }
        return journalEntries;
    }

    private JournalEntryDTO buildDTO(Cursor cursor) {
        return new JournalEntryDTO(cursor.getInt(cursor.getColumnIndex(ID)));
    }
}

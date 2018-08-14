package com.TeamAA.Beer_Journal.Database.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.TeamAA.Beer_Journal.Database.ByteHelper;
import com.TeamAA.Beer_Journal.Database.DTO.EntryPropertyDTO;
import com.TeamAA.Beer_Journal.Database.DTO.JournalEntryDTO;
import com.TeamAA.Beer_Journal.Exceptions.NoDataFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class provides a SQLite implementation of the EntryPropertyDAO
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 9:01 PM
 */
public class EntryPropertySQLITE implements EntryPropertyDAO {

    /**
     * The create table statement for the entryProperty table
     */
    public static final String CREATE_ENTRY_PROPS_TABLE = "create table " + ENTRY_PROPERTIES_TABLE +
            " (id integer primary key autoincrement, " +
            "unique(entry_id integer, name text) on conflict replace, " +
            "type text, " +
            "value text, " +
            "foreign key(entry_id) references journal_entry(id));";
    /**
     * The list of all of the columns in the database
     */
    private static final String[] ALL_COLUMNS = {ID, JOURNAL_ENTRY_ID, PROPERTY_NAME, PROPERTY_TYPE, VALUE};
    /**
     * The dbHelper is used to access the sqlite database
     */
    SQLiteDatabase db;

    public EntryPropertySQLITE(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public HashMap<String, EntryPropertyDTO> getEntryProperties(JournalEntryDTO journalEntry) throws NoDataFoundException {
        HashMap<String, EntryPropertyDTO> properties = new HashMap<String, EntryPropertyDTO>();
        Cursor cursor = db.query(ENTRY_PROPERTIES_TABLE, ALL_COLUMNS, "entry_id=?", new String[]{String.valueOf(journalEntry.getId())}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                EntryPropertyDTO property = buildDTO(cursor);
                properties.put(property.getPropertyName(), property);
            }
        } else {
            throw new NoDataFoundException("No data found for entry_id:" + journalEntry.getId());
        }

        if (cursor != null)
            cursor.close();

        return properties;
    }

    @Override
    public List<JournalEntryDTO> getJournalEntriesByPropValue(String propertyName, byte[] value) {
        List<JournalEntryDTO> journalEntries = new ArrayList<JournalEntryDTO>();
        String propertyHex = ByteHelper.bytesToHex(value);
        Cursor cursor = db.query(ENTRY_PROPERTIES_TABLE, new String[]{JOURNAL_ENTRY_ID}, "value=?", new String[]{propertyHex}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                journalEntries.add(new JournalEntryDTO(cursor.getInt(cursor.getColumnIndex(JOURNAL_ENTRY_ID))));
            }
        }

        if (cursor != null)
            cursor.close();

        return journalEntries;
    }

    @Override
    public EntryPropertyDTO saveOrUpdate(EntryPropertyDTO property) {
        EntryPropertyDTO newProperty = null;
        Cursor cursor = db.query(ENTRY_PROPERTIES_TABLE, new String[]{ID}, "id=?", new String[]{String.valueOf(property.getId())}, null, null, null);
        ContentValues values = new ContentValues();

        if (cursor != null && cursor.getCount() > 0) {
            //do an update. The only thing that can ever change is the property value
            values.put(VALUE, ByteHelper.bytesToHex(property.getValue()));
            db.update(ENTRY_PROPERTIES_TABLE, values, "id=?", new String[]{String.valueOf(property.getId())});
        } else {
            //insert
            values.put(JOURNAL_ENTRY_ID, property.getJournalEntryId());
            values.put(PROPERTY_NAME, property.getPropertyName());
            values.put(PROPERTY_TYPE, property.getPropertyType());
            values.put(VALUE, ByteHelper.bytesToHex(property.getValue()));
            long rowId = db.insert(ENTRY_PROPERTIES_TABLE, null, values);
            cursor = db.query(ENTRY_PROPERTIES_TABLE, new String[]{ID}, "rowid=?", new String[]{String.valueOf(rowId)}, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                newProperty = buildDTO(cursor);
            }
        }

        if (cursor != null)
            cursor.close();
        return newProperty;
    }

    @Override
    public HashMap<String, EntryPropertyDTO> saveOrUpdateAll(HashMap<String, EntryPropertyDTO> properties) {
        for (EntryPropertyDTO entryProperty : properties.values())
            properties.put(entryProperty.getPropertyName(), saveOrUpdate(entryProperty));
        return properties;
    }

    @Override
    public List<EntryPropertyDTO> getAll() {
        List<EntryPropertyDTO> entryProperties = new ArrayList<EntryPropertyDTO>();
        Cursor cursor = db.query(ENTRY_PROPERTIES_TABLE, ALL_COLUMNS, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                entryProperties.add(buildDTO(cursor));
            }
        }
        if (cursor != null)
            cursor.close();
        return entryProperties;
    }

    @Override
    public HashMap<String, EntryPropertyDTO> removeAllProperties(JournalEntryDTO journalEntry) {
        HashMap<String, EntryPropertyDTO> properties = journalEntry.getProperties();
        //delete each of the properties from the database
        for (EntryPropertyDTO entryProperty : properties.values()) {
            db.delete(ENTRY_PROPERTIES_TABLE, "id=?", new String[]{String.valueOf(entryProperty.getId())});
        }
        return properties;
    }

    private EntryPropertyDTO buildDTO(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        int entryId = cursor.getInt(cursor.getColumnIndex(JOURNAL_ENTRY_ID));
        String name = cursor.getString(cursor.getColumnIndex(PROPERTY_NAME));
        String type = cursor.getString(cursor.getColumnIndex(PROPERTY_TYPE));
        byte[] value = ByteHelper.hexToBytes(cursor.getString(cursor.getColumnIndex(VALUE)));
        return new EntryPropertyDTO(id, entryId, name, type, value);
    }
}

package com.TeamAA.Beer_Journal;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.TeamAA.Beer_Journal.Database.DAO.EntryPropertyDAO;
import com.TeamAA.Beer_Journal.Database.DAO.EntryPropertySQLITE;
import com.TeamAA.Beer_Journal.Database.DAO.JournalEntryDAO;
import com.TeamAA.Beer_Journal.Database.DAO.JournalEntrySQLITE;
import com.TeamAA.Beer_Journal.Database.JournalSQLiteDBHelper;
import com.TeamAA.Beer_Journal.PropertyTypes.PropertyType;

/**
 * This is the main context class for this application.
 * It has instances of the DAO objects that are used to interact with the database.
 * It also calls the PropertyType.loadPropertyTypes method when it's initialized.
 * Created with IntelliJ IDEA.
 * User: god
 * Date: 10/26/13
 * Time: 1:08 PM
 */
public class JournalController extends Activity {
    /**
     * This is the log tag to be used with android.util.Log
     */
    public static final String LOG_TAG = "beerJournal";
    /**
     * This is the instance of the JournalEntryDao that every class will use to interact with the database
     */
    private static JournalEntryDAO journalEntryDAO;
    /**
     * This is the instance of the EntryPropertyDao that every class will use to interact with the database
     */
    private static EntryPropertyDAO entryPropertyDAO;

    private static SQLiteDatabase databaseConnection;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize our DAO's
        JournalSQLiteDBHelper dbHelper = new JournalSQLiteDBHelper(getBaseContext());
        databaseConnection = dbHelper.getWritableDatabase();
        journalEntryDAO = new JournalEntrySQLITE(databaseConnection);
        entryPropertyDAO = new EntryPropertySQLITE(databaseConnection);

        //load all of our property types
        PropertyType.loadPropertyTypes(getBaseContext());
    }

    /**
     * This is the primary way to get the current instance, and therefore implementation, of the JournalEntryDAO
     *
     * @return The global instance/implementation of the JournalEntryDAO
     */
    public static JournalEntryDAO getJournalEntryDAO() {
        return journalEntryDAO;
    }

    /**
     * This is the primary way to get the current instance, and therefore implementation, of the EntryPropertyDAO
     *
     * @return The global instance/implementation of the EntryPropertyDAO
     */
    public static EntryPropertyDAO getEntryPropertyDAO() {
        return entryPropertyDAO;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close the database when the app is closed
        databaseConnection.close();
    }

    public static SQLiteDatabase getDatabaseConnection(){
        return databaseConnection;
    }
}

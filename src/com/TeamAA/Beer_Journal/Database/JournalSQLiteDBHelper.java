package com.TeamAA.Beer_Journal.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.TeamAA.Beer_Journal.Database.DAO.EntryPropertySQLITE;
import com.TeamAA.Beer_Journal.Database.DAO.JournalEntrySQLITE;

/**
 * This class provides a way to access and create a SQLite database on android
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 6:56 PM
 */
public class JournalSQLiteDBHelper extends SQLiteOpenHelper {
    /**
     * The database version. This will be changed if the database structure ever needs to be changed
     */
    public static final int SQLITE_DB_VERSION = 1;

    /**
     * The name of the database
     */
    public static final String DATABASE_NAME = "journalDB";


    public JournalSQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SQLITE_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(JournalEntrySQLITE.CREATE_JOURNAL_ENTRY_TABLE);
        sqLiteDatabase.execSQL(EntryPropertySQLITE.CREATE_ENTRY_PROPS_TABLE);
    }

    @Override
    /**
     * This method is only used if the database version is changed.
     * It will handle making the actual changes that need to be done.
     */
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        //Always make sure foreign keys are enabled
        if (!sqLiteDatabase.isReadOnly()) {
            sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

}

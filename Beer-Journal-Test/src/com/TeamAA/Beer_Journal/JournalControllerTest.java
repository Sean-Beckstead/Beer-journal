package com.TeamAA.Beer_Journal;

import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import com.TeamAA.Beer_Journal.Database.DAO.EntryPropertyDAO;
import com.TeamAA.Beer_Journal.Database.JournalSQLiteDBHelper;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.TeamAA.Beer_Journal.JournalControllerTest \
 * com.TeamAA.Beer_Journal.tests/android.test.InstrumentationTestRunner
 */
public class JournalControllerTest extends ActivityInstrumentationTestCase2<JournalController> {
    JournalController journalController;
    public JournalControllerTest() {
        super("com.TeamAA.Beer_Journal", JournalController.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (journalController != null){
            journalController.getBaseContext().deleteDatabase(JournalSQLiteDBHelper.DATABASE_NAME);
        }
    }
}

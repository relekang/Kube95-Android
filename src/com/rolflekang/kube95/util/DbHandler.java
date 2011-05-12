package com.rolflekang.kube95.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHandler {
	// Database keys
    public static final String KEY_NAME = "name";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_ROWID = "_id";
    
    // Some other useful variables
    private static final String TAG = "teXtyDbHandler";
    private final Context mCtx;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    // CREATE query
    private static final String DATABASE_CREATE = 
    	"CREATE TABLE IF NOT EXISTS entries (_id integer primary key autoincrement, "
        + "name text not null, message text not null, number text not null);";
    
    // Database info
    private static final String DATABASE_NAME = "texty";
    private static final String DATABASE_TABLE = "entries";
    private static final int DATABASE_VERSION = 3;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
    	
    	DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", trying to implement new database structure");
            db.execSQL("ALTER TABLE instances RENAME TO tmp_entries;");
            db.execSQL(DATABASE_CREATE);
            db.execSQL("INSERT INTO entries(name,message,number) " +
            		   "SELECT name,codename,number " +
            		   "FROM tmp_entries;");
            db.execSQL("DROP TABLE tmp_entries;");
        }
    }
    
    public DbHandler(Context ctx) {
        mCtx = ctx;
    }

    public DbHandler open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createEntry(String name, String message, String number) {
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_NAME, name);
	    initialValues.put(KEY_MESSAGE, message);
	    initialValues.put(KEY_NUMBER, number);
	    
	    // Returns -1 if errors occurred. The current rowId if not.
	    return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

    public boolean deleteEntry(long rowId) {
    	// Returns the number of affected rows on success, else 0
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public Cursor fetchAllEntries() {
    	// Returns all info for all the instances
        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    
    public Cursor fetchEntry(long rowId) throws SQLException {
    	// Returns all info on one specific instance
	    return mDb.query(true, DATABASE_TABLE, null, KEY_ROWID + "=" + rowId, null, null, null, null, null);
    }
    
    public int getCount() {
    	Cursor numRows = mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID}, null, null, null, null, null);
    	// Returns the number of rows in the database
    	return numRows.getCount();
    }

    public boolean updateEntry(long rowId, String name, String message, String number) {
        ContentValues updateValues = new ContentValues();        
        updateValues.put(KEY_NAME, name);
        updateValues.put(KEY_MESSAGE, message);
        updateValues.put(KEY_NUMBER, number);

        // Returns the number of affected rows
        return mDb.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean isOpen() {
    	return mDb.isOpen();
    }
}


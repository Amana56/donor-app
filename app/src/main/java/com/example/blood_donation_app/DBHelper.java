package com.example.blood_donation_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    // Database name
    private static final String DATABASE_NAME = "blood_donation.db";
    private static final int DATABASE_VERSION = 1;

    // Table structure for blood donations
    public static class BloodDonationEntry implements BaseColumns {
        public static final String TABLE_NAME = "blood_donations";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_BLOOD_TYPE = "blood_type";
    }

    // Table structure for blood requests
    public static class BloodRequestEntry implements BaseColumns {
        public static final String TABLE_NAME = "blood_requests";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BLOOD_TYPE = "blood_type";
        public static final String COLUMN_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_HOSPITAL_NAME = "hospital_name";
        public static final String COLUMN_ADDITIONAL_NOTES = "additional_notes";
    }

    // Table structure for sign-up data
    public static class SignUpEntry implements BaseColumns {
        public static final String TABLE_NAME = "signup";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
    }

    // SQL statement for creating the table for blood donations
    private static final String SQL_CREATE_BLOOD_DONATION_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + BloodDonationEntry.TABLE_NAME + " (" +
                    BloodDonationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BloodDonationEntry.COLUMN_NAME + " TEXT NOT NULL," +
                    BloodDonationEntry.COLUMN_BLOOD_TYPE + " TEXT NOT NULL," +
                    BloodDonationEntry.COLUMN_AGE + " INTEGER NOT NULL," +
                    BloodDonationEntry.COLUMN_WEIGHT + " INTEGER NOT NULL," +
                    BloodDonationEntry.COLUMN_CONTACT_NUMBER + " TEXT NOT NULL)";

    // SQL statement for creating the table for blood requests
    private static final String SQL_CREATE_BLOOD_REQUEST_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + BloodRequestEntry.TABLE_NAME + " (" +
                    BloodRequestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BloodRequestEntry.COLUMN_NAME + " TEXT NOT NULL," +
                    BloodRequestEntry.COLUMN_BLOOD_TYPE + " TEXT NOT NULL," +
                    BloodRequestEntry.COLUMN_CONTACT_NUMBER + " TEXT NOT NULL," +
                    BloodRequestEntry.COLUMN_HOSPITAL_NAME + " TEXT NOT NULL," +
                    BloodRequestEntry.COLUMN_ADDITIONAL_NOTES + " TEXT)";

    // SQL statement for creating the table for sign-up data
    private static final String SQL_CREATE_SIGNUP_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + SignUpEntry.TABLE_NAME + " (" +
                    SignUpEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SignUpEntry.COLUMN_NAME + " TEXT NOT NULL," +
                    SignUpEntry.COLUMN_EMAIL + " TEXT NOT NULL," +
                    SignUpEntry.COLUMN_PASSWORD + " TEXT NOT NULL)";

    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BLOOD_DONATION_ENTRIES);
        db.execSQL(SQL_CREATE_BLOOD_REQUEST_ENTRIES);
        db.execSQL(SQL_CREATE_SIGNUP_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BloodDonationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BloodRequestEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SignUpEntry.TABLE_NAME);
        onCreate(db);
    }

    // Insert blood donation data into the database
    public boolean insertBloodDonationData(String name, String bloodType, int age, int weight, String contactNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BloodDonationEntry.COLUMN_NAME, name);
        contentValues.put(BloodDonationEntry.COLUMN_BLOOD_TYPE, bloodType);
        contentValues.put(BloodDonationEntry.COLUMN_AGE, age);
        contentValues.put(BloodDonationEntry.COLUMN_WEIGHT, weight);
        contentValues.put(BloodDonationEntry.COLUMN_CONTACT_NUMBER, contactNumber);

        long result = db.insert(BloodDonationEntry.TABLE_NAME, null, contentValues);
        if (result != -1) {
            Toast.makeText(mContext, "Blood donation data inserted successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(mContext, "Failed to insert blood donation data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Insert blood request data into the database
    public boolean insertBloodRequestData(String name, String bloodType, String contactNumber,
                                          String hospitalName, String additionalNotes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BloodRequestEntry.COLUMN_NAME, name);
        contentValues.put(BloodRequestEntry.COLUMN_BLOOD_TYPE, bloodType);
        contentValues.put(BloodRequestEntry.COLUMN_CONTACT_NUMBER, contactNumber);
        contentValues.put(BloodRequestEntry.COLUMN_HOSPITAL_NAME, hospitalName);
        contentValues.put(BloodRequestEntry.COLUMN_ADDITIONAL_NOTES, additionalNotes);

        long result = db.insert(BloodRequestEntry.TABLE_NAME, null, contentValues);
        if (result != -1) {
            Toast.makeText(mContext, "Blood request submitted successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(mContext, "Failed to submit blood request", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Insert user sign-up data into the database
    public boolean insertUserData(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SignUpEntry.COLUMN_NAME, name);
        contentValues.put(SignUpEntry.COLUMN_EMAIL, email);
        contentValues.put(SignUpEntry.COLUMN_PASSWORD, password);

        long result = db.insert(SignUpEntry.TABLE_NAME, null, contentValues);
        if (result != -1) {
            Toast.makeText(mContext, "Sign-up successful", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(mContext, "Sign-up failed", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Check if user exists in the database and if password matches
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {SignUpEntry.COLUMN_EMAIL, SignUpEntry.COLUMN_PASSWORD};
        String selection = SignUpEntry.COLUMN_EMAIL + " = ? AND " + SignUpEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(
                SignUpEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        boolean signInSuccess = cursor.getCount() > 0;
        cursor.close();
        return signInSuccess;
    }
}

package com.example.blood_donation_app;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RequestViewActivity extends AppCompatActivity {

    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_view_layout);

        // Initialize DBHelper
        mDBHelper = new DBHelper(this);

        // Get the parent layout where dynamic views will be added
        LinearLayout parentLayout = findViewById(R.id.request_info_container);

        // Retrieve data from the database
        Cursor cursor = mDBHelper.getReadableDatabase().rawQuery("SELECT * FROM " + DBHelper.BloodRequestEntry.TABLE_NAME, null);

        // Check if cursor has data
        if (cursor.moveToFirst()) {
            do {
                // Inflate a layout for each blood request item
                LinearLayout itemLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_request, null);

                // Find TextViews in the inflated layout
                TextView textName = itemLayout.findViewById(R.id.textName);
                TextView textBloodType = itemLayout.findViewById(R.id.textBloodType);
                TextView textHospitalName = itemLayout.findViewById(R.id.textHospitalName);
                TextView textAdditionalNotes = itemLayout.findViewById(R.id.textAdditionalNotes);

                // Populate TextViews with data from cursor
                textName.setText("Name: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodRequestEntry.COLUMN_NAME)));
                textBloodType.setText("Blood Type: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodRequestEntry.COLUMN_BLOOD_TYPE)));
                textHospitalName.setText("Hospital Name: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodRequestEntry.COLUMN_HOSPITAL_NAME)));
                textAdditionalNotes.setText("Additional Notes: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodRequestEntry.COLUMN_ADDITIONAL_NOTES)));

                // Add the inflated layout to the parent layout
                parentLayout.addView(itemLayout);
            } while (cursor.moveToNext());
        }

        // Close cursor
        cursor.close();
    }
}

package com.example.blood_donation_app;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DonorViewActivity extends AppCompatActivity {

    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_view_layout);

        // Initialize DBHelper
        mDBHelper = new DBHelper(this);

        // Get the parent layout where dynamic views will be added
        LinearLayout parentLayout = findViewById(R.id.donor_info_container);

        // Retrieve data from the database
        Cursor cursor = mDBHelper.getReadableDatabase().rawQuery("SELECT * FROM " + DBHelper.BloodDonationEntry.TABLE_NAME, null);

        // Check if cursor has data
        if (cursor.moveToFirst()) {
            do {
                // Inflate a layout for each donor item
                LinearLayout itemLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_donor, null);

                // Find TextViews in the inflated layout
                TextView textDonorName = itemLayout.findViewById(R.id.textDonorName);
                TextView textBloodType = itemLayout.findViewById(R.id.textBloodType);
                TextView textDonorAge = itemLayout.findViewById(R.id.textDonorAge);
                TextView textDonorWeight = itemLayout.findViewById(R.id.textDonorWeight);
                TextView textContactNumber = itemLayout.findViewById(R.id.textContactNumber);

                // Populate TextViews with data from cursor
                textDonorName.setText("Donor Name: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodDonationEntry.COLUMN_NAME)));
                textBloodType.setText("Blood Type: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodDonationEntry.COLUMN_BLOOD_TYPE)));
                textDonorAge.setText("Age: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodDonationEntry.COLUMN_AGE)));
                textDonorWeight.setText("Weight: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodDonationEntry.COLUMN_WEIGHT)) + " kg");
                textContactNumber.setText("Contact Number: " + cursor.getString(cursor.getColumnIndex(DBHelper.BloodDonationEntry.COLUMN_CONTACT_NUMBER)));

                // Add the inflated layout to the parent layout
                parentLayout.addView(itemLayout);
            } while (cursor.moveToNext());
        }

        // Close cursor
        cursor.close();
    }
}

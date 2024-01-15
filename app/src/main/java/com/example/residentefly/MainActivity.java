package com.example.residentefly;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.residentefly.domain.MyBookingInfo;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private CheckBox residenteCheckBox;
    private Spinner airportDepartureSpn, airportDestinationSpn;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private final MyBookingInfo bookingInfo = new MyBookingInfo();
    private Button sendBtn;

    // Flags to track if selections have been made
    private boolean isDateSelected = false;
    private boolean isDepartureSelected = false;
    private boolean isDestinationSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checking for internet access permission
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No se ha dado permiso de INTERNET", Toast.LENGTH_SHORT).show();
        }

        residenteCheckBox = findViewById(R.id.residenteCheckBox);

        sendBtn = findViewById(R.id.buscarButton);
        sendBtn.setEnabled(false); // Disable the sendBtn initially
        airportDepartureSpn = findViewById(R.id.aeropuertoDepartureSpinner);
        airportDestinationSpn = findViewById(R.id.aeropuertoDestinationSpinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.airports,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        airportDepartureSpn.setAdapter(adapter);
        airportDestinationSpn.setAdapter(adapter);
        //Set String[] variable to the selected airport on each spinner
        airportDepartureSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bookingInfo.setDepartureCode(airportDepartureSpn.getSelectedItem().toString().substring(0, 3));
                isDepartureSelected = true;
                checkAllSelected(bookingInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                isDepartureSelected = false;
                checkAllSelected(bookingInfo);
            }
        });
        airportDestinationSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bookingInfo.setArrivalCode(airportDestinationSpn.getSelectedItem().toString().substring(0, 3));
                isDestinationSelected = true;
                checkAllSelected(bookingInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                isDestinationSelected = false;
                checkAllSelected(bookingInfo);
            }
        });

        dateButton = findViewById(R.id.dateButton);
        dateButton.setOnClickListener(view -> {
            // Get current date
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);

            // Initialize DatePickerDialog
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year, monthOfYear, dayOfMonth) -> {
                        // Set selected date to the dateButton
                        String dateSelected = String.format(Locale.forLanguageTag("es-ES"), "%02d-%02d-%d", dayOfMonth, monthOfYear + 1, year);
                        dateButton.setText(dateSelected);
                        // Save the selected date to bookingInfo
                        bookingInfo.setDate(String.format(Locale.forLanguageTag("es-ES"), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                        isDateSelected = true;
                        checkAllSelected(bookingInfo);
                    }, mYear, mMonth, mDay);

            // Set the DatePicker minimum date to current date
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            // Set the first day of the week to Monday
            datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);

            datePickerDialog.show();
        });

        // Need to set adults number selector. Between 1 and 9.
        bookingInfo.setAdultsNumber(1);


        sendBtn.setOnClickListener(view -> {

            // Get the status of the checkbox
            boolean residente = residenteCheckBox.isChecked();

            // Sending data to other activity
            Intent intent = new Intent(getApplicationContext(), MainActivity2FlightsView.class);
            intent.putExtra("FLY_OFFERS", bookingInfo);
            // Add the status of the checkbox as an extra
            intent.putExtra("RESIDENTE", residente);
            // Starting next Activity
            startActivity(intent);

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.activity_main);
    }

    private void checkAllSelected(MyBookingInfo bookingInfo) {
        boolean isDifferentAirports = !bookingInfo.getDepartureCode().equals(bookingInfo.getArrivalCode());
        sendBtn.setEnabled(isDateSelected && isDepartureSelected && isDestinationSelected && isDifferentAirports);
    }

}
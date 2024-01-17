package com.example.residentefly;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.residentefly.api.FlightInformationService;
import com.example.residentefly.domain.MyBookingInfo;
import com.example.residentefly.domain.flightoffer.FlightOffer;
import com.example.residentefly.domain.flightoffer.FlightOfferRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity2FlightsView extends AppCompatActivity {

    MyBookingInfo bookingInfo;
    private MainActivity2FlightsView.AsyncInformationServiceCall asyncCall;
    private RecyclerView recyclerView;
    private FlightOfferCardAdapter flightOfferCardAdapter;
    private boolean residente;
    private boolean eligibleResidente;
    private SwitchCompat buttonSwitch;
    private Spinner sortSpinner;
    private String sortSpinnerSelection = "Precio";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_flights_view);

        // Get the intent that started this activity
        Intent intent = getIntent();
        // Get the booking info
        bookingInfo = (MyBookingInfo) intent.getSerializableExtra("FLY_OFFERS");
        // Get the status of the checkbox
        residente = intent.getBooleanExtra("RESIDENTE", false);

        // Check if the user is eligible for the residente discount
        eligibleResidente = isEligibleResidente(residente, bookingInfo.getDepartureCode(), bookingInfo.getArrivalCode());

        recyclerView = findViewById(R.id.recyclerView);

        // Animator OFF
        recyclerView.setItemAnimator(null);

        asyncCall = new MainActivity2FlightsView.AsyncInformationServiceCall(this);
        asyncCall.execute(bookingInfo);

    }

    private void setupRecyclerView(List<FlightOffer> flightOffers) {
        if (!isFinishing()) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            flightOfferCardAdapter = new FlightOfferCardAdapter(flightOffers, eligibleResidente);
            recyclerView.setAdapter(flightOfferCardAdapter);
        }
    }

    boolean isEligibleResidente(boolean residente, String departureIATACode, String arrivalIATACode) {
        boolean conditionCheck = false;
        String[] islasCanarias = {"LPA", "ACE", "TFN", "TFS", "GMZ", "VDE", "SPC", "FUE"};
        List<String> islasCanariasList = Arrays.asList(islasCanarias);

        if (residente && (islasCanariasList.contains(departureIATACode) || islasCanariasList.contains(arrivalIATACode))) {
            conditionCheck = true;
        }

        return conditionCheck;
    }

    List<FlightOffer> filterSortFlightOffer(List<FlightOffer> flightOffers, String sortMode, boolean isSwitchChecked) {

        Stream<FlightOffer> flightOfferStream = flightOffers.stream();
        if (isSwitchChecked) {
            flightOfferStream = flightOfferStream.filter(flightOffer -> flightOffer.isDirect());
        }
        if ("Duracion".equals(sortMode)) {
            flightOfferStream = flightOfferStream.sorted(Comparator.comparing(FlightOffer::getDuration));
        } else {
            flightOfferStream = flightOfferStream.sorted(Comparator.comparing((FlightOffer flightOffer) -> flightOffer.getPrice().getAmount()));
        }

        return flightOfferStream.collect(Collectors.toList());

        /*
        // Alternative way to do the same thing
        return flightOffers.stream()
                .filter(flightOffer ->  isSwitchChecked ? flightOffer.isDirect() : true )
                .sorted(sortMode == "Precio" ?
                        Comparator.comparing((FlightOffer flightOffer) -> flightOffer.getPrice().getAmount()) :
                        Comparator.comparing(FlightOffer::getDuration))
                .collect(Collectors.toList());
         */
    }

    private class AsyncInformationServiceCall extends AsyncTask<MyBookingInfo, Integer, List<FlightOffer>> {
        ProgressDialog progressDialog;
        private final Context context;

        public AsyncInformationServiceCall(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity2FlightsView.this);
            progressDialog.setMessage("Retrieving Flight Information");
            progressDialog.show();
        }

        @Override
        protected List<FlightOffer> doInBackground(@NonNull MyBookingInfo... myBookingInfos) {
            MyBookingInfo myBookingInfo = myBookingInfos[0];
            try {
                FlightOfferRepository repository = new FlightInformationService();
                return repository.getFlightOffers(myBookingInfo);
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }

        @Override
        protected void onPostExecute(List<FlightOffer> flightOffers) {

            buttonSwitch = findViewById(R.id.switchSoloDirectos);
            sortSpinner = findViewById(R.id.sortSpiner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.sortSpinner,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sortSpinner.setAdapter(adapter);
            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                    sortSpinnerSelection = sortSpinner.getSelectedItem().toString();
                    setupRecyclerView(filterSortFlightOffer(flightOffers, sortSpinnerSelection, buttonSwitch.isChecked()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            CompoundButton buttonSwitch = findViewById(R.id.switchSoloDirectos);
            buttonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    setupRecyclerView(filterSortFlightOffer(flightOffers, sortSpinnerSelection, isChecked));
                }
            });

            setupRecyclerView(filterSortFlightOffer(flightOffers, sortSpinnerSelection, buttonSwitch.isChecked()));

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

}
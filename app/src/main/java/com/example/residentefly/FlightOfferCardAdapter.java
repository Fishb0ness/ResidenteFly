package com.example.residentefly;

import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.residentefly.domain.Money;
import com.example.residentefly.domain.flightoffer.Flight;
import com.example.residentefly.domain.flightoffer.FlightOffer;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FlightOfferCardAdapter extends RecyclerView.Adapter<FlightOfferCardAdapter.ViewHolder> {

    private final List<FlightOffer> flightOffers;
    private final boolean elegibleResidente;
    private final SparseBooleanArray expandedItems = new SparseBooleanArray();

    public FlightOfferCardAdapter(List<FlightOffer> flightOffers, boolean elegibleResidente) {
        this.flightOffers = flightOffers;
        this.elegibleResidente = elegibleResidente;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fligth_offer_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlightOffer flightOffer = flightOffers.get(position);

        // Get the total price
        Money totalPrice;
        if (!elegibleResidente) {
            totalPrice = flightOffer.getPrice();
        } else {
            totalPrice = flightOffer.getResidentePrice();
        }

        holder.textAirlineDeparture.setText(flightOffer.getFirstFlight().getAirline().getName());
        if (flightOffer.getFlights().size() > 1) {
            holder.textAirlineArrival.setText(flightOffer.getLastFlight().getAirline().getName());
            // if the airline of the departure is different than the airline of the arrival, show the airline of the arrival
            if (!holder.textAirlineDeparture.getText().equals(holder.textAirlineArrival.getText())) {
                holder.textAirlineArrival.setVisibility(View.VISIBLE);
            }
        }
        ZonedDateTime dateDepartureDeparture = flightOffer.getFirstFlight().getDepartureTime().atZone(ZoneOffset.systemDefault());
        ZonedDateTime dateDepartureArrival = flightOffer.getFirstFlight().getArrivalTime().atZone(ZoneOffset.systemDefault());
        ZonedDateTime dateArrivalDeparture = flightOffer.getLastFlight().getDepartureTime().atZone(ZoneOffset.systemDefault());
        ZonedDateTime dateArrivalArrival = flightOffer.getLastFlight().getArrivalTime().atZone(ZoneOffset.systemDefault());

        //if there is more than one stop, show the departure and arrival time of the first and last flight
        holder.textDepartureTime.setText(DateTimeFormatter.ofPattern("HH:mm").format(dateDepartureDeparture));
        holder.textDepartureArrivalTime.setText(DateTimeFormatter.ofPattern("HH:mm").format(dateDepartureArrival));
        holder.textDepartureArrivalTime.setVisibility(expandedItems.get(position) ? View.VISIBLE : View.GONE);
        holder.textArrivalTime.setText(expandedItems.get(position) ?
                DateTimeFormatter.ofPattern("HH:mm").format(dateArrivalDeparture) : DateTimeFormatter.ofPattern("HH:mm").format(dateArrivalArrival));
        holder.textArrivalArrivalTime.setText(DateTimeFormatter.ofPattern("HH:mm").format(dateArrivalArrival));
        holder.textArrivalArrivalTime.setVisibility(expandedItems.get(position) ? View.VISIBLE : View.GONE);

        holder.textFlightDuration.setText("Duracion: " + durationToString(flightOffer.getDuration()));

        // calculate the difference in days between arrival and departure
        int differenceDays = dateArrivalArrival.getDayOfMonth() - dateDepartureDeparture.getDayOfMonth();
        // if departure date is different than arrival date, show difference in days
        if (differenceDays > 0) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            holder.textDepartureDate.setText(dateTimeFormatter.format(dateDepartureDeparture));
            holder.textDifferenceDays.setText("+" + differenceDays + " día(s)");
            holder.textArrivalDate.setText(dateTimeFormatter.format(dateArrivalArrival));
            holder.textDepartureDate.setVisibility(View.VISIBLE);
            holder.textDifferenceDays.setVisibility(View.VISIBLE);
            holder.textArrivalDate.setVisibility(View.VISIBLE);
        } else {
            holder.textDepartureDate.setVisibility(View.GONE);
            holder.textDifferenceDays.setVisibility(View.GONE);
            holder.textArrivalDate.setVisibility(View.GONE);
        }


        holder.textDeparture.setText(flightOffer.getFirstFlight().getDepartureAirport().getCode());
        holder.textArrival.setText(flightOffer.getLastFlight().getArrivalAirport().getCode());
        holder.textPrice.setText(String.format(Locale.forLanguageTag("es-ES"), "%.2f %S", totalPrice.getAmount(), totalPrice.getCurrency().getCurrencyCode()));

        //check if there is more than one stop to show the number of stops or if it is direct
        holder.textNumeroEscalas.setText(flightOffer.isDirect() ? "Directo" : "Escalas: " + (flightOffer.getFlights().size() - 1));
        holder.textArrivalEscala.setText(flightOffer.getFirstFlight().getArrivalAirport().getName());
        holder.textArrivalEscala.setVisibility(expandedItems.get(position) ? View.VISIBLE : View.GONE);

        //Additional data on click
        boolean isExpanded = expandedItems.get(position, false);
        holder.itemView.setOnClickListener(v -> {
            if (!flightOffer.isDirect()) {
                if (isExpanded) {
                    expandedItems.delete(position);
                } else {
                    expandedItems.put(position, true);
                }
                notifyItemChanged(position);
            }
        });

        // button to open the web page of the airline
        String websiteText = flightOffer.getFirstFlight().getAirline().getWebsite();
        if (websiteText.startsWith("http://") || websiteText.startsWith("https://")) {
            holder.buttonAirLineWeb.setText("ver en la web de la aerolínea");
        } else {
            holder.buttonAirLineWeb.setText("No disponible");
        }
        holder.buttonAirLineWeb.setOnClickListener(v -> {
            if (websiteText.startsWith("http://") || websiteText.startsWith("https://")) {
                // Open the web page of the airline
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(flightOffer.getFirstFlight().getAirline().getWebsite()));
                v.getContext().startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return flightOffers.size();
    }

    // Method to change date format from "yyyy-mm-ddThh:mm:ss" to Instant
    private Instant changeDateFormat(String date) {
        return Instant.parse(date + "Z");
    }

    private String durationToString(Duration duration) {
        return String.format("%d:%02d",
                duration.toHours(),
                duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textAirlineDeparture, textAirlineArrival,
                textDepartureTime, textFlightDuration, textArrivalTime,
                textDepartureDate, textDifferenceDays, textArrivalDate,
                textDeparture, textArrival,
                textNumeroEscalas, textArrivalEscala, textDepartureEscala,
                textDepartureArrivalTime, textArrivalArrivalTime,
                textPrice;

        Button buttonAirLineWeb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textAirlineDeparture = itemView.findViewById(R.id.textAirlineDeparture);
            textAirlineArrival = itemView.findViewById(R.id.textAirlineArrival);
            textDepartureTime = itemView.findViewById(R.id.textDepartureTime);
            textFlightDuration = itemView.findViewById(R.id.textFlightDuration);
            textArrivalTime = itemView.findViewById(R.id.textArrivalTime);
            textDepartureDate = itemView.findViewById(R.id.textDepartureDate);
            textDifferenceDays = itemView.findViewById(R.id.textDifferenceDays);
            textArrivalDate = itemView.findViewById(R.id.textArrivalDate);
            textDeparture = itemView.findViewById(R.id.textDeparture);
            textArrival = itemView.findViewById(R.id.textArrival);
            textPrice = itemView.findViewById(R.id.textPrice);
            textDepartureArrivalTime = itemView.findViewById(R.id.textDepartureArrivalTime);
            textArrivalArrivalTime = itemView.findViewById(R.id.textArrivalArrivalTime);
            textNumeroEscalas = itemView.findViewById(R.id.txEscalas);
            textArrivalEscala = itemView.findViewById(R.id.textArrivalEscala);
            textDepartureEscala = itemView.findViewById(R.id.textDepartureEscala);
            buttonAirLineWeb = itemView.findViewById(R.id.buttonAirLineWeb);
        }
    }

}


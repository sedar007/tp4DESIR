package fr.ulco.tp4desir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class GeoActivity extends ActivityComponent {

    private final static String GEO_URI = "geo:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_geo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onValidateButtonClick(View view) {
        TextView latitudeView = findViewById(R.id.latitude);
        String latitudeString = latitudeView.getText().toString();

        TextView longitudeView = findViewById(R.id.longitude);
        String longitudeString = longitudeView.getText().toString();

        // Vérification que les coordonnées sont valides
        if(!isValidCoordinates(latitudeString, longitudeString))
            return;
        startActivity(GEO_URI + latitudeString + "," + longitudeString, Intent.ACTION_VIEW);
    }

    private Boolean isValidCoordinates(String latitude, String longitude){
        if(inputIsEmpty(latitude, getString(R.string.invalid_latitude_toast)))
            return false;

        if(inputIsEmpty(longitude, getString(R.string.invalid_longitude_toast)))
            return false;

        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);

            if(lat < -90 || lat > 90){
                Toast.makeText(this, getString(R.string.invalid_latitude_toast), Toast.LENGTH_SHORT).show();
                return false;
            }

            if(lon < -180 || lon > 180){
                Toast.makeText(this, getString(R.string.invalid_longitude_toast), Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.invalid_coordinates_toast), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
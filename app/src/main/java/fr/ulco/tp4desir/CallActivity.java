package fr.ulco.tp4desir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallActivity extends ActivityComponent {

    private final static String TEL_URI = "tel:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    /// Bouton Valider qui lance l'application d'appel
    public void onValidateButtonClick(View view){
        TextView textView = findViewById(R.id.number);
        String numberString = textView.getText().toString();

        // Vérification que le numéro est valide
        if(!isPhoneNumberValid(numberString))
            return;

        startActivity(TEL_URI + numberString, Intent.ACTION_DIAL);

    }

    private boolean isPhoneNumberValid(String numberString) {
        // Vérification que le numéro n'est pas vide
        if (inputIsEmpty(numberString, getString(R.string.invalid_number_toast)))
            return false;
        // vérification que le numéro est bien composé de chiffres uniquement
        return isvalidPhoneNumber(numberString);
    }
}
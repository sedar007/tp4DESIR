package fr.ulco.tp4desir;

import android.content.Intent;
import android.net.Uri;
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

public class MessageActivity extends ActivityComponent {

    private final static String SMS_URI = "sms:";
    private final static String MMS_URI = "mms:";
    private final static String SMS_BODY = "sms_body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onValidateButtonClick(View view) {
        TextView numberView = findViewById(R.id.number);
        String numberString = numberView.getText().toString();

        TextView messageView = findViewById(R.id.message);
        String messageString = messageView.getText().toString();

        if (!isInputValid(numberString, messageString))
            return;

        // Récupération de l'intent pour savoir si on envoie un SMS ou un MMS
        Intent intent = getIntent();

        boolean isSms = intent.getBooleanExtra(MainActivity.SMS, true);
        boolean isMms = intent.getBooleanExtra(MainActivity.MMS, true);

        // Construction de l'URI en fonction du type de message
        String URI = isSms ? SMS_URI : isMms ? MMS_URI : null;
        if(URI == null){
            Toast.makeText(this, getString(R.string.no_app_available_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> extras = new HashMap<>();
        extras.put(SMS_BODY, messageString);

        startActivity(URI + numberString, Intent.ACTION_SENDTO, extras);
    }

    private boolean isInputValid(String numberString, String messageString) {
        // Vérification que le numéro n'est pas vide
        if(inputIsEmpty(numberString, getString(R.string.invalid_number_toast)))
            return false;

        // vérification que le numéro est bien composé de chiffres uniquement
        if(!isvalidPhoneNumber(numberString))
            return false;

        // Vérification que le message n'est pas vide
        if(inputIsEmpty(messageString, getString(R.string.empty_message_toast)))
            return false;

        return true;
    }

}
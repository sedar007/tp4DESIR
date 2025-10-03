package fr.ulco.tp4desir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebActivity extends ActivityComponent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onValidateButtonClick(View view) {
        TextView urlView = findViewById(R.id.url);
        String urlString = urlView.getText().toString();

        // Vérification que l'URL n'est pas vide
        if(!isValidUrl(urlString))
            return;

        startActivity(urlString, Intent.ACTION_VIEW);
    }

    /// Vérifie que l'URL est valide
    private Boolean isValidUrl(String url){
        if(inputIsEmpty(url, getString(R.string.invalid_number_toast)))
            return false;

        if(!android.util.Patterns.WEB_URL.matcher(url).matches()){
            Toast.makeText(this, getString(R.string.invalid_url_toast), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
package fr.ulco.tp4desir;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public abstract class ActivityComponent extends AppCompatActivity {

    public abstract void onValidateButtonClick(View view);

    /// Bouton Annuler : termine l'activité sans rien faire
    public void onCancelButtonClick(View view){
        finish();
    }

    /// Vérifie si une entrée est vide, affiche un message si c'est le cas
    protected Boolean inputIsEmpty(final String text, final String message) {
        if (text.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    /// Vérifie si un numéro de téléphone est valide
    protected Boolean isvalidPhoneNumber(final String number) {
        String phonePattern = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";

        if(!number.matches(phonePattern)){
            Toast.makeText(this, getString(R.string.invalid_number_toast), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    protected void startActivity(final String uriString, final String intentAction){
        Intent intent = new Intent();
        Uri uri = Uri.parse(uriString);
        intent.setData(uri);
        intent.setAction(intentAction);

        startActivity_(intent);
    }

    private void startActivity_(Intent intent){
        try{
            startActivity(intent);
        } catch (Exception e){
            Toast.makeText(this, getString(R.string.no_app_available_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        finish();
    }

    protected void startActivity(final String uriString, final String intentAction, Map<String, String> extras){
        Intent intent = new Intent();
        Uri uri = Uri.parse(uriString);
        intent.setData(uri);
        intent.setAction(intentAction);

        for(Map.Entry<String, String> entry : extras.entrySet()){
            intent.putExtra(entry.getKey(), entry.getValue());
        }

        startActivity_(intent);
    }





}

package fr.ulco.tp4desir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public final static String SMS = "fr.ulco.tp4desir.MainActivity.SMS";
    public final static String MMS = "fr.ulco.tp4desir.MainActivity.MMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void startActivityOnClick(final Intent intent, final String toastMessage ){
        Toast.makeText(this,toastMessage , Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void onButtonClick(View view){

        if(view.getId() == R.id.callButton){
            startActivityOnClick(new Intent(this, CallActivity.class),
                    getString(R.string.main_activity_call_button));
        }
        else if (view.getId() == R.id.smsButton) {
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra(SMS, true);
            startActivityOnClick(intent, getString(R.string.main_activity_sms_button));

        }
        else if (view.getId() == R.id.mmsButton) {
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra(MMS, true);
            startActivityOnClick(intent, getString(R.string.main_activity_mms_button));
        }
       else if (view.getId() == R.id.webButton) {
            startActivityOnClick(new Intent(this, WebActivity.class), getString(R.string.main_activity_web_button));
        }

       else if (view.getId() == R.id.geoButton) {
            startActivityOnClick(new Intent(this, GeoActivity.class), getString(R.string.main_activity_geo_button));
        }
    }

}
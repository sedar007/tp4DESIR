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


    public void onButtonClick(View view){
        Intent intent;
        if(view.getId() == R.id.callButton){
            intent = new Intent(this, CallActivity.class);
        }
        else if (view.getId() == R.id.smsButton) {
            intent = new Intent(this, MessageActivity.class);
            intent.putExtra(SMS, true);

        }
        else if (view.getId() == R.id.mmsButton) {
            intent = new Intent(this, MessageActivity.class);
            intent.putExtra(MMS, true);
        }
       else if (view.getId() == R.id.webButton) {
            intent = new Intent(this, WebActivity.class);
        }

       else if (view.getId() == R.id.geoButton) {
            intent = new Intent(this, GeoActivity.class);
        }
        else {
              Toast.makeText(this, getString(R.string.no_app_available_toast), Toast.LENGTH_SHORT).show();
              return;
          }

        startActivity(intent);
    }

}
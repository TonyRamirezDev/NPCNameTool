package tonyram.npcnametool;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends Activity {

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public static final String TAG = "MyActivity";


    private void init() {
        final TextView newNameText = (TextView)findViewById(R.id.nameValue);
        Button randomizeButton = (Button)findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStreamReader firstNameStream = new InputStreamReader(getResources().openRawResource(R.raw.firstnames));
                BufferedReader br = new BufferedReader(firstNameStream);
                int numLinesFirstName = 100;
                Random randomFirstName = new Random();
                int desiredLine = randomFirstName.nextInt(numLinesFirstName);

                String newName = "";
                int lineCtr = 0;

                try {
                    while ((newName = br.readLine()) != null){
                        if (lineCtr == desiredLine){
                            break;
                        }
                        lineCtr++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "The new name is: " + newName);
                newNameText.setText(newName);
            }
        });

    }

}



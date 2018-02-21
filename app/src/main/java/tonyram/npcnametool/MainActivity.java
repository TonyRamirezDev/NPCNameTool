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
                InputStreamReader lastNameStream = new InputStreamReader(getResources().openRawResource(R.raw.lastnames));
                BufferedReader brFirstName = new BufferedReader(firstNameStream);
                BufferedReader brLastName = new BufferedReader(lastNameStream);
                int numLinesFirstName = 100;
                int numLinesLastName = 70;
                Random randomFirstName = new Random();
                Random randomLastName = new Random();
                int desiredLineFirstName = randomFirstName.nextInt(numLinesFirstName);
                int desiredLineLastName = randomLastName.nextInt(numLinesLastName);

                String newFirstName = "";
                String newLastName = "";
                int lineCounterFirstName = 0;
                int lineCounterLastName = 0;

                StringBuilder fullName = new StringBuilder();

                try {
                    while ((newFirstName = brFirstName.readLine()) != null){
                        if (lineCounterFirstName == desiredLineFirstName){
                            break;
                        }
                        lineCounterFirstName++;
                    }
                    while ((newLastName = brLastName.readLine()) != null){
                        if (lineCounterLastName == desiredLineLastName) {
                            break;
                        }
                        lineCounterLastName++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //newNameText.setText(newFirstName + " " + newLastName);
                fullName.append(newFirstName);
                fullName.append(" ");
                fullName.append(newLastName);
                newNameText.setText(fullName);
            }
        });

    }

}



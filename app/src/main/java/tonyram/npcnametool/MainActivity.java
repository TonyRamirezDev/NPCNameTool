package tonyram.npcnametool;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends AppCompatActivity {


    InputStreamReader firstNameStream;
    InputStreamReader lastNameStream;
    BufferedReader brFirstName;
    BufferedReader brLastName;
    int numLinesFirstName;
    int numLinesLastName;
    Random randomFirstName;
    Random randomLastName;
    int desiredLineFirstName;
    int desiredLineLastName;
    String newFirstName;
    String newLastName;
    int lineCounterFirstName;
    int lineCounterLastName;
    StringBuilder fullName;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar);
        init();
    }

    public static final String TAG = "MainActivity";


    private void init() {
        //Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Randomize Name
        final TextView newNameText = (TextView)findViewById(R.id.nameValue);
        Button randomizeButton = (Button)findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameStream = new InputStreamReader(getResources().openRawResource(R.raw.firstnames));
                lastNameStream = new InputStreamReader(getResources().openRawResource(R.raw.lastnames));
                brFirstName = new BufferedReader(firstNameStream);
                brLastName = new BufferedReader(lastNameStream);
                numLinesFirstName = 100;
                numLinesLastName = 70;
                randomFirstName = new Random();
                randomLastName = new Random();
                desiredLineFirstName = randomFirstName.nextInt(numLinesFirstName);
                desiredLineLastName = randomLastName.nextInt(numLinesLastName);
                newFirstName = "";
                newLastName = "";
                lineCounterFirstName = 0;
                lineCounterLastName = 0;
                fullName = new StringBuilder();
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
                fullName.append(newFirstName);
                fullName.append(" ");
                fullName.append(newLastName);
                newNameText.setText(fullName);
            }
        });

        //Add Saved name to List
        ListView savedList = (ListView)findViewById(R.id.listSaved);
        Button saveButton = (Button)findViewById(R.id.saveButton);
        final EditText descriptionText = (EditText)findViewById(R.id.descriptionInput);
        final EditText locationText = (EditText)findViewById(R.id.locationInput);
        final ArrayList<String> listItems = new ArrayList<String>();
        final ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(this, R.layout.saved_list, listItems);
        //savedList.setAdapter(stringAdapter);

        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                listItems.add(fullName.toString());
                listItems.add(descriptionText.getText().toString());
                listItems.add(locationText.getText().toString());
                stringAdapter.notifyDataSetChanged();
            }

        });

//        savedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                //do something
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}



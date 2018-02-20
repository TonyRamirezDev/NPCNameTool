package tonyram.npcnametool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }



    private void init() {
        Button randomizeButton = (Button)findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameValue = (TextView)findViewById(R.id.nameValue);
                nameValue.setText("New Name");
            }
        });

    }




}



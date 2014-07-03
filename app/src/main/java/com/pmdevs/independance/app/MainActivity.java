package com.pmdevs.independance.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends Activity {
    Button submitButton;
    EditText zipCode;
    Spinner radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (Button)findViewById(R.id.button);
        zipCode = (EditText)findViewById(R.id.zipIn);
        radius = (Spinner)findViewById(R.id.radiusIn);

        submitButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (getApplicationContext(), ListEvents.class);
                        intent.putExtra("ZIP_INFO",zipCode.toString());
//                        intent.putExtra("RADIUS",radius.toString());
                        startActivity(intent);
                    }
                }
        );
    }




}

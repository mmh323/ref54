// WelcomeActivity.java
package com.example.referat54;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // Setzt das Layout für die Aktivität

        Button startButton = findViewById(R.id.startButton); // Initialisiert den Start-Button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Wechsel zur InstructionActivity
                Intent intent = new Intent(WelcomeActivity.this, InstructionActivity.class);
                startActivity(intent); // Startet die InstructionActivity
            }
        });
    }
}

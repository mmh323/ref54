// CommentDialog.java
package com.example.referat54;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;

public class CommentDialog extends Dialog {
    private EditText commentInput; // Eingabefeld für den Kommentar
    private Button sendButton, cancelButton; // Schaltflächen zum Senden und Abbrechen

    // Konstruktor für den Kommentar-Dialog
    public CommentDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_dialog); // Layout für den Dialog festlegen

        commentInput = findViewById(R.id.commentInput); // Verweis auf das Kommentar-Eingabefeld
        // sendButton = findViewById(R.id.sendButton); // Verweis auf die Senden-Schaltfläche
        // cancelButton = findViewById(R.id.cancelButton); // Verweis auf die Abbrechen-Schaltfläche

        sendButton.setOnClickListener(view -> {
            String comment = commentInput.getText().toString(); // Kommentartext abrufen
            // Kommentar senden und verarbeiten
            dismiss(); // Dialog schließen
        });

        cancelButton.setOnClickListener(view -> dismiss()); // Dialog beim Abbrechen schließen
    }
}

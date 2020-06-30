package com.example.maihotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    Context contxt;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contxt = this;

        username = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _email_ = String.valueOf(username.getText());
                String _password_ = String.valueOf(password.getText());

                databaseHelper = new DatabaseHelper(MainActivity.this);
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isExist = databaseHelper.checkUserExist(username.toString(), password.toString());

                        if (isExist) {
                            Intent intent = new Intent(MainActivity.this, UserHomeActivity.class);
                            intent.putExtra("username", username.getText().toString());
                            startActivity(intent);
                        } else {
                            password.setText(null);
                            Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
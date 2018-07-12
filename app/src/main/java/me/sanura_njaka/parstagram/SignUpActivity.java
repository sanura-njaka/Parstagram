package me.sanura_njaka.parstagram;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText newPasswordInput;
    private EditText confirmPasswordInput;
    private EditText emailInput;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = findViewById(R.id.etNewUsername);
        newPasswordInput = findViewById(R.id.etNewPass);
        confirmPasswordInput = findViewById(R.id.etConfirmPass);
        emailInput = findViewById(R.id.etEmail);
        signUpBtn = findViewById(R.id.btnNewSignUp);

        signUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = confirmPasswordInput.getText().toString();
                final String email = emailInput.getText().toString();

                if (!password.equals(newPasswordInput.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_LONG).show();
                    return;
                }

                signUp(username, password, email);
            }
        });
    }

    private void signUp(final String username, final String password, String email) {
        final ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        //user.put("profile_pic", );

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("SignUpActivity", "Sign up success");
                    login(username, password);
                } else {
                    Log.e("SignUpActivity", "Sign up failure");
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("SignUpActivity", "Sign up and login success");
                    final Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("SignUpActivity", "Login after sign up failure");
                    e.printStackTrace();
                }
            }
        });
    }
}

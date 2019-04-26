package com.sharedPreference.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sharedPreference.R;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private Button signUp;
    private EditText editTextFName, editTextLName, editTextPassword, editTextConfirmPassword, editTextEmail, editTextPhoneNmber;
    private String fName, lName, email, password, confirmPassword, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
        /*initialization of id*/
        inItId();
        /*listener for button */
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueFromUser();
            }
        });
    }
    private void getValueFromUser() {
        fName = editTextFName.getText().toString();
        lName = editTextLName.getText().toString();
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        confirmPassword = editTextConfirmPassword.getText().toString();
        phoneNumber = editTextPhoneNmber.getText().toString();
        if (nameValidation(fName) && stringContainNumber(fName)) {
            if (nameValidation(lName) && stringContainNumber(lName)) {
                if (!email.isEmpty() && isValidEmail(email)) {
                    if (isPasswordValid(password)) {
                        if (isPasswordValid(confirmPassword) && isConfirmPasswordMatch(confirmPassword)) {
                            if (!phoneNumber.isEmpty() && isPhoneNumberValid(phoneNumber)) {
                                /*set data in the shared preference*/
                               // setUserDataInSharedPreference();
                                /*pass data through intent*/
                                passDataByIntent();
                            } else {
                                Toast.makeText(this, "Phone Number is must", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "Confirm Password is must", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(this, "Password is must", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "Email is must", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "LastName is must", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "FirstName is must", Toast.LENGTH_SHORT).show();
        }
    }

    private void passDataByIntent() {
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        Bundle extras = new Bundle();
        extras.putString("fName", fName);
        extras.putString("lName", lName);
        extras.putString("email", email);
        extras.putString("password", password);
        extras.putString("confirmPassword", confirmPassword);
        extras.putString("phoneNumber", phoneNumber);
        intent.putExtras(extras);
        startActivity(intent);
    }


    private void inItId() {
        editTextFName = findViewById(R.id.et_fName);
        editTextLName = findViewById(R.id.et_lName);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        editTextPhoneNmber = findViewById(R.id.et_phoneNumber);
        editTextConfirmPassword = findViewById(R.id.et_confirmPassword);
        signUp = findViewById(R.id.btn_signUp);
    }
    /*to check string is empty or not equal to null*/
    private Boolean nameValidation(String userInput) {
        if (!userInput.isEmpty() && userInput != null) {
            return true;
        } else {
            return false;
        }
    }
    /*to check string have an number or not*/
    private Boolean stringContainNumber(String userInput) {
        if (Pattern.compile("[0-9]").matcher(userInput).find()) {
            return false;
        } else {
            return true;
        }
    }
    /*to check email is valid or not*/
    private boolean isValidEmail(String userInput) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(userInput).matches()) {
            // Toast.makeText(this, "right ", Toast.LENGTH_SHORT).show();
            return true; }
            else {
            // Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            return false; }
    }
    /*to check password is valid or not*/
    private boolean isPasswordValid(String userInput) {
        if (!userInput.isEmpty() && userInput != null) {
            return true;
        } else {
            return false;
        }
    }
    /*to check password is match with confirm password*/
    private boolean isConfirmPasswordMatch(String userInput) {
        if (userInput.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    /*to check phone number is valid or not*/
    private boolean isPhoneNumberValid(String userInput) {
        if (Patterns.PHONE.matcher(userInput).matches()) {
            return true;
        } else {
            return false;
        }
    }
}

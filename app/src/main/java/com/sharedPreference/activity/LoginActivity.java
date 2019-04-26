package com.sharedPreference.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sharedPreference.R;

public class LoginActivity extends AppCompatActivity {
    private TextView textViewSignUp;
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private String uEmail, uPassword, sharedEmail, sharedPassword;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*initialization of id*/
        intItId();
        /*check if shared preference have value then redirect the user on home page*/
       if(getSharedPreferenceValue())
       {
           Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
           startActivity(intent);
           finish();
       }
       else if(isBundleEmptyForIntentData())
       {
           /* get data by intent*/
           getDataFromIntent();
           /*get the value from the shared preference*/
           getSharedPreferenceValue();
           /*listener for textView for going to signUp*/
           textViewSignUp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                   startActivity(intent);
               }
           });
           /* listener for login */
           buttonLogin.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   /*get the value from the user*/
                   getUserInput();
                   if (isValidEmail(uEmail)) {
                       if (isPasswordValid(uPassword)) {  /*to check user credential is correct or not*/
                           isAuthenticate();
                       } else {
                           Toast.makeText(LoginActivity.this, "Password is must", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(LoginActivity.this, "Email is must", Toast.LENGTH_SHORT).show();
                   }
               }
           });

       }
       else {

           /*listener for textView for going to signUp*/
           textViewSignUp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                   startActivity(intent);
               }
           });
           /* listener for login */
           buttonLogin.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   /*get the value from the user*/
                   getUserInput();
                   if (isValidEmail(uEmail)) {
                       if (isPasswordValid(uPassword)) {  /*to check user credential is correct or not*/
                           isAuthenticate();
                       } else {
                           Toast.makeText(LoginActivity.this, "Password is must", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(LoginActivity.this, "Email is must", Toast.LENGTH_SHORT).show();
                   }
               }
           });

       }

    }

    private boolean isBundleEmptyForIntentData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras !=null)
        {
            return true;
        }
        else {
            return false;
        }
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String fName = extras.getString("fName","");
        Log.i(TAG, "getDataFromIntent: "+fName);
        String lName = extras.getString("lName","");
        String email = extras.getString("email");
        String password = extras.getString("password");
        String confirmPassword = extras.getString("confirmPassword");
        String phoneNumber = extras.getString("phoneNumber");

        /*to keep the data in shared preference */
        putDataInSharedPreference(fName,lName,email,password,confirmPassword,phoneNumber);

    }

    private void putDataInSharedPreference(String sharedFName,String sharedLName,String sharedUEmail,
                                           String sharedUPassword,String sharedUConfirmPassword,String sharedPhoneNumber) {
        SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        editor.putString("fName", sharedFName);
        editor.putString("lName", sharedLName);
        editor.putString("email", sharedUEmail);
        editor.putString("password", sharedUPassword);
        editor.putString("confirmPassword", sharedUConfirmPassword);
        editor.putString("phoneNumber", sharedPhoneNumber);
        editor.apply();
    }

    private void isAuthenticate() {
        /*to check sharedPreference is null or empty*/
        if (isSharedPreferenceHaveValue()) {
            if (isUserCredentailCorrect()) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Credentials not correct", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Shared Preference have no value", Toast.LENGTH_SHORT).show();
        }

    }

    private Boolean isUserCredentailCorrect() {
        if (sharedEmail.equals(uEmail) && sharedPassword.equals(uPassword)) {
            return true;
        } else {
            return false;
        }

    }

    private Boolean isSharedPreferenceHaveValue() {
        if (checkNullOrEmptyValidation(sharedEmail) && checkNullOrEmptyValidation(sharedPassword)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean getSharedPreferenceValue() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        sharedEmail = sharedPreferences.getString("email", "");
        sharedPassword = sharedPreferences.getString("password", "");
        if(checkNullOrEmptyValidation(sharedEmail) && checkNullOrEmptyValidation(sharedPassword))
        {//Log.i(TAG, "getSharedPreferenceValue: " + sharedEmail);
            return true;
        }
        else {
            return false;
        }

    }

    private void getUserInput() {
        uEmail = editTextEmail.getText().toString();
        uPassword = editTextPassword.getText().toString();
        Log.i(TAG, "getUserInput: "+ uEmail +""+uPassword);
    }

    private void intItId() {
        textViewSignUp = findViewById(R.id.signUp);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_login);
    }

    /*to check string is empty or not equal to null*/
    private Boolean checkNullOrEmptyValidation(String userInput) {
        if (!userInput.isEmpty() && userInput != null) {
            return true;
        } else {
            return false;
        }
    }

    /*to check password is valid or not*/
    private boolean isPasswordValid(String userInput) {
        if (!userInput.isEmpty() && userInput != null) {
            return true;
        } else {
            return false;
        }
    }

    /*to check email is valid or not*/
    private boolean isValidEmail(String userInput) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(userInput).matches()) {
            // Toast.makeText(this, "right ", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}

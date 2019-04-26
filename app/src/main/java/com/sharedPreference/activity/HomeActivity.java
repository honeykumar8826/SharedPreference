package com.sharedPreference.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sharedPreference.R;

public class HomeActivity extends AppCompatActivity {
    private String sharedEmail, sharedPassword, sharedFirstName, sharedLastName, sharedConfirmPassword, sharedPhoneNumber;
    private TextView textViewFName, textViewlName, textViewEmail, textViewPassword, textViewConfirmPassword, textViewphoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*get the value from the shared preference*/
        getSharedPreferenceValue();
        /*initialization of id*/
        intItId();
        /*set the value on textview*/
        setDataOnTextView();
    }

    private void setDataOnTextView() {
        textViewFName.setText(sharedFirstName);
        textViewlName.setText(sharedLastName);
        textViewEmail.setText(sharedEmail);
        textViewPassword.setText(sharedPassword);
        textViewConfirmPassword.setText(sharedConfirmPassword);
        textViewphoneNumber.setText(sharedPhoneNumber);
    }

    private void intItId() {
        textViewFName = findViewById(R.id.tv_fName);
        textViewlName = findViewById(R.id.tv_lName);
        textViewEmail = findViewById(R.id.tv_email);
        textViewPassword = findViewById(R.id.tv_password);
        textViewConfirmPassword = findViewById(R.id.tv_confirm_password);
        textViewphoneNumber = findViewById(R.id.tv_phone_number);
    }

    private void getSharedPreferenceValue() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        sharedFirstName = sharedPreferences.getString("fName", "");
        sharedLastName = sharedPreferences.getString("lName", "");
        sharedEmail = sharedPreferences.getString("email", "");
        sharedPassword = sharedPreferences.getString("password", "");
        sharedConfirmPassword = sharedPreferences.getString("confirmPassword", "");
        sharedPhoneNumber = sharedPreferences.getString("phoneNumber", "");

        //Log.i(TAG, "getSharedPreferenceValue: " + sharedEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.clear:
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                return true;

                default:
                    Toast.makeText(this, "Do Right Selection", Toast.LENGTH_SHORT).show();
                    return true;
        }

    }
}

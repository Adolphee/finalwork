package ehb.adolphe.finalwork.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;

import ehb.adolphe.finalwork.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private NestedScrollView nestedScrollView;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatCheckBox appCompatCheckBox;

    private SharedPreferences preferences;
    private SharedPreferences.Editor pEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        checkSharedPreferences();
        initListeners();

    }

    private void initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById((R.id.textInputLayoutPassword));

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);
        appCompatCheckBox = findViewById(R.id.appCompatCheckBox);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        pEditor = preferences.edit();
        pEditor.apply();

    }

    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
    }

   @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                saveAccount();
                Intent mainRegister = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainRegister);
                break;
        }
    }


    private void saveAccount() {
        if (appCompatCheckBox.isChecked()) {

            //save checkbox
            pEditor.putString(getString(R.string.text_remember_me), "True");
            pEditor.apply();

            //save the username
            String pTextInputEditTextEmail = textInputEditTextEmail.getText().toString();
            pEditor.putString(getString(R.string.hint_email), pTextInputEditTextEmail);
            pEditor.apply();
            //save the email
            String pTextInputEditTextPassword = textInputEditTextPassword.getText().toString();
            pEditor.putString(getString(R.string.hint_password), pTextInputEditTextPassword);
            pEditor.apply();

        } else {

            //save checkbox
            pEditor.putString(getString(R.string.text_remember_me), "False");
            pEditor.apply();

            //save the username
            String pTextInputEditTextEmail = textInputEditTextEmail.getText().toString();
            pEditor.putString(getString(R.string.hint_email), "");
            pEditor.apply();
            //save the email
            String pTextInputEditTextPassword = textInputEditTextPassword.getText().toString();
            pEditor.putString(getString(R.string.hint_password), "");
            pEditor.apply();

        }
    }

    private void checkSharedPreferences() {
        String pAppCompatCheckBox = preferences.getString(getString(R.string.text_remember_me), "False");
        String pTextInputEditTextEmail = preferences.getString(getString(R.string.hint_email), "");
        String pTextInputEditTextPassword = preferences.getString(getString(R.string.hint_password), "");

        textInputEditTextEmail.setText(pTextInputEditTextEmail);
        textInputEditTextPassword.setText(pTextInputEditTextPassword);

        if (pAppCompatCheckBox.equals("True")) {
            appCompatCheckBox.setChecked(true);
        } else {
            appCompatCheckBox.setChecked(false);
        }
    }



}


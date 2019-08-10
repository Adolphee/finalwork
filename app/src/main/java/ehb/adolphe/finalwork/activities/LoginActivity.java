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
import android.util.Log;
import android.view.View;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.LoginForm;
import ehb.adolphe.finalwork.model.Student;
import ehb.adolphe.finalwork.retrofit.RetroHelper;
import ehb.adolphe.finalwork.retrofit.services.StudentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private NestedScrollView nestedScrollView;

    private TextInputEditText login_email;
    private TextInputEditText login_pwd;

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

        login_email = findViewById(R.id.textInputEditTextEmail);
        login_pwd = findViewById(R.id.textInputEditTextPassword);

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
                onSubmitLogin();
                break;
        }
    }


    private void saveAccount() {
        if (appCompatCheckBox.isChecked()) {

            //save checkbox
            pEditor.putString(getString(R.string.text_remember_me), "True");
            pEditor.apply();

            //save the username
            String plogin_email = login_email.getText().toString();
            pEditor.putString(getString(R.string.hint_email), plogin_email);
            pEditor.apply();
            //save the email
            String plogin_pwd = login_pwd.getText().toString();
            pEditor.putString(getString(R.string.hint_password), plogin_pwd);
            pEditor.apply();

        } else {

            //save checkbox
            pEditor.putString(getString(R.string.text_remember_me), "False");
            pEditor.apply();

            //save the username
            String plogin_email = login_email.getText().toString();
            pEditor.putString(getString(R.string.hint_email), "");
            pEditor.apply();
            //save the email
            String plogin_pwd = login_pwd.getText().toString();
            pEditor.putString(getString(R.string.hint_password), "");
            pEditor.apply();

        }
    }

    private void checkSharedPreferences() {
        String pAppCompatCheckBox = preferences.getString(getString(R.string.text_remember_me), "False");
        String plogin_email = preferences.getString(getString(R.string.hint_email), "");
        String plogin_pwd = preferences.getString(getString(R.string.hint_password), "");

        login_email.setText(plogin_email);
        login_pwd.setText(plogin_pwd);

        if (pAppCompatCheckBox.equals("True")) {
            appCompatCheckBox.setChecked(true);
        } else {
            appCompatCheckBox.setChecked(false);
        }
    }
    
    void onSubmitLogin(){
        Retrofit retrofit = RetroHelper.getInstance();

        StudentService studentService = retrofit.create(StudentService.class);

        LoginForm form = new LoginForm();
        form.setEmail(login_email.getText().toString());
        form.setPassword(login_pwd.getText().toString());

        Call<Student> call = studentService.authenticate(form);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(!response.isSuccessful()) {
                    Log.d( "LOGIN","onResponse: " + response.code());
                    return;
                }
                MainActivity.AUTH_USER = response.body();
                Intent mainRegister = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainRegister);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("LOGIN", "onFailure: " + t.getMessage());
            }
        });
    }



}


package sr.unasat.tvat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.tvat.R;
import sr.unasat.tvat.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {
EditText firstName, lastName, userName, password, confirmPassword, email;
    String firstNameString, lastNameString, userNameString, passwordString, confirmPasswordString, emailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = (EditText) findViewById(R.id.firstNameRegText);
        lastName = (EditText)findViewById(R.id.lastNameRegText);
        userName = (EditText)findViewById(R.id.userNameRegText);
        password = (EditText)findViewById(R.id.passwordRegText);
        confirmPassword = (EditText)findViewById(R.id.confirmPasswordRegText);
        email = (EditText)findViewById(R.id.emailRegText);

    }
//firstname lastname username password email
    public void registerUser(View view){

        firstNameString = firstName.getText().toString();
        lastNameString = lastName.getText().toString();
        userNameString = userName.getText().toString();
        passwordString = password.getText().toString();
        emailString = email.getText().toString();

        UserDAO db = new UserDAO(this);
        db.setUser(db, firstNameString, lastNameString, userNameString, passwordString,emailString);
        Toast.makeText(this,"Account geregistreerd",Toast.LENGTH_SHORT).show();
//Toast.makeText(this, "Gebruikersnaam of wachtwoord niet ingevuld", Toast.LENGTH_SHORT).show();

    }
}


/*public class RegisterActivity extends AppCompatActivity {
EditText USER_NAME,USER_PASS,CON_PASS;
    String user_name,user_pass,con_pass;
    Button REG;
    Context ct = this;//waarom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Intent register = getIntent();
        USER_NAME = (EditText)findViewById(R.id.rEditTextUserName);
        USER_PASS = (EditText)findViewById(R.id.rEditTextPassword);
        CON_PASS = (EditText) findViewById(R.id.rEditTextConfirmPassword);
        REG = (Button)findViewById(R.id.registerButton);
    }
    public void registerUser(View view){
        user_name = USER_NAME.getText().toString();
        user_pass = USER_PASS.getText().toString();
        con_pass = CON_PASS.getText().toString();

        if (!(user_pass.equals(con_pass))){
            Toast.makeText(this,"Wachtwoorden komen niet overeen",Toast.LENGTH_SHORT).show();
            USER_PASS.setText(null);
            CON_PASS.setText(null);
        }
        else if(user_name.isEmpty()||user_pass.isEmpty()||con_pass.isEmpty()){
            Toast.makeText(this,"U heeft niet alle velden ingevuld",Toast.LENGTH_SHORT).show();
        }
        else {
          /*LoggerDAO DBO = new LoggerDAO(ct);
            DBO.insertInformation(DBO,user_name,user_pass);*/
    /*
LoggerDAO DB = new LoggerDAO(ct);
DB.insertInformation(DB,user_name,user_pass);
        Toast.makeText(this,"Registratie succesvol",Toast.LENGTH_SHORT).show();
        // finish();
        }
        }
        } */
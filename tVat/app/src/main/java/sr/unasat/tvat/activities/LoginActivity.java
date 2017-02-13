package sr.unasat.tvat.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.tvat.R;
import sr.unasat.tvat.dao.UserDAO;
import sr.unasat.tvat.entities.User;

public class LoginActivity extends AppCompatActivity {
   String pPassword;
    String uUsername;
    User user;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDAO = new UserDAO(this);
    }

    public  void registerScreen(View view){
        Intent loginToRegister = new Intent(this,RegisterActivity.class);
        startActivity(loginToRegister);
    }

    public void loginUser(View view){
        EditText userName = (EditText)findViewById(R.id.userNameLoginText);
        EditText password = (EditText)findViewById(R.id.passwordLoginText);

        String userNameString = userName.getText().toString();
        String passwordString = password.getText().toString();
        if(userNameString.isEmpty()||passwordString.isEmpty()){
            Toast.makeText(this, "Gebruikersnaam of wachtwoord niet ingevuld", Toast.LENGTH_SHORT).show();

        }else if(validCredentials(userNameString,passwordString)){
            Intent loginToWelcome = new Intent(this,WelcomeActivity.class);
            loginToWelcome.putExtra("username",userNameString);
            startActivity(loginToWelcome);
        }
        else {
            Toast.makeText(this, "Gebruikersnaam of wachtwoord is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validCredentials(String userNameString, String passwordString) {
        User user = userDAO.getUserByUsername(userNameString);

        if(user!=null && userNameString.equals(user.getUserName())&&passwordString.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    }


/*
Intent loginToWelcome = new Intent(this,WelcomeActivity.class);
            loginToWelcome.putExtra("username",userNameString);
            startActivity(loginToWelcome);

else {
            Toast.makeText(this, "Gebruikersnaam of wachtwoord is incorrect", Toast.LENGTH_SHORT).show();
        }

        else if(userNameString.isEmpty()||passwordString.isEmpty()){
            Toast.makeText(this, "Gebruikersnaam of wachtwoord niet ingevuld", Toast.LENGTH_SHORT).show();
        }
        */
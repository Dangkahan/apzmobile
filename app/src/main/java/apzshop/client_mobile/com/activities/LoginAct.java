package apzshop.client_mobile.com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apzshop.client_mobile.com.R;

public class LoginAct extends AppCompatActivity {

    EditText username,password;
    Button btnlogin,signuptxt;
    ClientDb DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.Username1);
        password = findViewById(R.id.Password1);
        btnlogin = findViewById(R.id.Loginbtn1);
        signuptxt = findViewById(R.id.signuptxt);
        DB = new ClientDb(this);

        btnlogin.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("")||pass.equals(""))
                Toast.makeText(LoginAct.this, "Please fill all that blank.", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkuserpassword(user, pass);
                if(checkuserpass){
                    Toast.makeText(LoginAct.this, "You're now logged in.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeAct.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginAct.this, "Wrong Credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signuptxt.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
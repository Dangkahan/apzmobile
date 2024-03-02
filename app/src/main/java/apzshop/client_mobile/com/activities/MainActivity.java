package apzshop.client_mobile.com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.view.View;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apzshop.client_mobile.com.R;

public class MainActivity extends AppCompatActivity {

    EditText username, email, password, repassword;
    Button Register, Login;
    ClientDb DB;
    private Firebase auth;

    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();
        auth = Firebase.getInstance();

        username = findViewById(R.id.Username);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        repassword = findViewById(R.id.Repassword);
        Register = findViewById(R.id.Signupbtn);
        Login = findViewById(R.id.Loginbtn);
        DB = new ClientDb(this);

        sharedPreferences = getSharedPreferences("HomeAct", MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

        if (isFirstTime){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent intent = new Intent(MainActivity.this,HomeAct.class);
            startActivity(intent);
            finish();

        }

        Register.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();


            if(user.equals("")||pass.equals("")||repass.equals(""))
                Toast.makeText(MainActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkuser = DB.checkusername(user);
                    if(!checkuser){
                        Boolean insert = DB.insertData(user,pass);
                        if(insert){
                            Toast.makeText(MainActivity.this, "Successfully register!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginAct.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "User Existed! Please proceed login.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Incorrect/Not match password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),LoginAct.class);
            startActivity(intent);

        });

    }

    private static class Firebase {
        public static Firebase getInstance() {
            return null;
        }
    }

}
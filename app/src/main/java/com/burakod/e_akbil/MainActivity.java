package com.burakod.e_akbil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ClipData.newIntent;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Firabase Control" ;
    EditText password,userName;
    Button btnLogin;
    FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }



    private void init() {
        btnLogin=(Button) findViewById(R.id.btnLogin);
        userName=(EditText) findViewById(R.id.userName);
        password=(EditText) findViewById(R.id.password);

    }

    public void Login(View view) {

        String email = userName.getText().toString();
        String pass = password.getText().toString();
        LoginControl(email,pass);




    }

    private void LoginControl(String email, String pass) {

        if (user != null) {
            // Name, email address, and profile photo Url
           email = user.getEmail();


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Giriş Başarılı.",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
                        }
                        else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"Bilgiler Hatalı.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

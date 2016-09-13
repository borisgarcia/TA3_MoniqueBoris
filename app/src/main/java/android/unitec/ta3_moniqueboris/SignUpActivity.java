package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    static final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);

    }

    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    public void crear(View view){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(saveUser(email,pass)){
            Intent intent = getIntent();
            intent.putExtra(EMAIL_KEY,email);
            setResult(RESULT_OK,intent);
            finish();
        }
        else{
            Toast.makeText(SignUpActivity.this, "Este correo ya esta ingresado", Toast.LENGTH_LONG).show();
        }

    }

    protected boolean saveUser(String email, String pass){
        SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_PREFERENCES,MODE_PRIVATE);

        String resultado = sp.getString(email,"");
        if(resultado.equals("")){
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(email,pass);
            edit.commit();
            return true;
        }
        return false;
    }



}

package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPass;
    static final int CREAR_REQUEST = 0;
    static final String SHARED_PREFERENCES = "login";
    static final String EMAIL = "email";
    CheckBox remem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        remem = (CheckBox)findViewById(R.id.cbRecord);
        CargarPreferencias();
        Intent i = getIntent();
        boolean check = i.getBooleanExtra("BOOLEAN",true);

        if(remem.isChecked()&&check){
            Intent goAuto = new Intent(getBaseContext(),AutoLoginActivity.class);
            goAuto.putExtra(EMAIL,etEmail.getText().toString());

            startActivity(goAuto);
        }

        remem.setChecked(check);

    }

    public void login(View view){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        String email = etEmail.getText().toString();
        String resultado = sp.getString(email,"");

        if(resultado.equals("")){
            Toast.makeText(LoginActivity.this, "Ese usuario no existe", Toast.LENGTH_LONG).show();
        }
        else{
            GuardarPreferencias();
            Intent intent = new Intent ( LoginActivity.this, ProfileActivity.class );
            intent.putExtra (EMAIL,etEmail.getText().toString());
            startActivity(intent);
        }
    }

    public void CargarPreferencias(){
        SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_PREFERENCES,MODE_PRIVATE);
        remem.setChecked(sp.getBoolean("CHECKBOX",false));
        etEmail.setText(sp.getString("EMAIL",""));
    }

    public void GuardarPreferencias(){
        SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        boolean valor = remem.isChecked();
        String email = etEmail.getText().toString();
        edit.putBoolean("CHECKBOX",valor);
        edit.putString("EMAIL",email);
        edit.commit();
    }



    public void signUp(View view){
        Intent go2 = new Intent(getBaseContext(),SignUpActivity.class);
        startActivityForResult(go2,CREAR_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CREAR_REQUEST){
            if(resultCode == RESULT_OK){
                String email = data.getStringExtra(SignUpActivity.EMAIL_KEY);
                Toast.makeText(LoginActivity.this, email + " ingresado", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(LoginActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
        }

    }


}

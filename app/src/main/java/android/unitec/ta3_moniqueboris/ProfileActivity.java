package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    EditText etEmail, etEgresos, etIngresos, etI_E;
    int egresos = 0,ingresos = 0, resta = 0;
    static final String EGRE = "egresos";
    static final String INGRE = "ingresos";
    static final String EMAILL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        LoadInt();
        etEmail = (EditText) (findViewById(R.id.editText));
        etIngresos = (EditText) (findViewById(R.id.editText2));
        etEgresos = (EditText) (findViewById(R.id.editText3));
        etI_E = (EditText) (findViewById(R.id.editText4));

        DisableET();

        Intent i = getIntent();
        ingresos += i.getIntExtra(TransactActivity.INGRESOS,0);
        egresos += i.getIntExtra(TransactActivity.EGRESOS,0);



        resta = ingresos - egresos;
        etEmail.setText(i.getStringExtra(LoginActivity.EMAIL));
        etEgresos.setText(egresos+"");
        etIngresos.setText(ingresos+"");
        etI_E.setText(resta+"");

    }

    public void SaveInt(String key, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public void LoadInt(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ingresos = sp.getInt("Ingresos", 0);
        egresos = sp.getInt("Egresos",0);
    }

    public void Grafica(View view) {
        Intent intent = new Intent ( ProfileActivity.this, GraphActivity.class );
        intent.putExtra(EMAILL,etEmail.getText().toString());
        intent.putExtra (INGRE, ingresos );
        intent.putExtra (EGRE, egresos );
        startActivity(intent);
    }


    public void transcaccion(View view) {
        Intent go2 = new Intent(getBaseContext(), TransactActivity.class);
        startActivityForResult(go2, 0);
    }

    public void DisableET(){
        etEmail.setEnabled(false);
        etIngresos.setEnabled(false);
        etI_E.setEnabled(false);
        etEgresos.setEnabled(false);
    }

    public void Salvar(View view) {
        SaveInt("Ingresos",ingresos);
        SaveInt("Egresos",egresos);
    }


}

package android.unitec.ta3_moniqueboris;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


        etI_E.setText(resta+"");

        load();
    }

    public void SavePref(){
        SharedPreferences sp = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("Ingresos",etIngresos.getText().toString());
        edit.putString("Egresos",etEgresos.getText().toString());
        edit.commit();
    }

    public void load(){
        SharedPreferences sp = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        etIngresos.setText(sp.getString("Ingresos",""));
        etEgresos.setText(sp.getString("Egresos",""));
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
        SavePref("Ingresos", etIngresos.getText().toString());
        SavePref("Egresos",Integer.parseInt(etIngresos.getText().toString());
        finish();
    }


}

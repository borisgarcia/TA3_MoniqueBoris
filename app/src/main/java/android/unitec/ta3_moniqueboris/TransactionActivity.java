package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    EditText etMonto;
    Spinner tipo;
    static String egresos, ingresos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        etMonto = (EditText)findViewById(R.id.etMonto);
        tipo = (Spinner)findViewById(R.id.spinner);
        Integer monto = Integer.parseInt(etMonto.getText().toString());

    }

    public void aplicar(View view){
        Intent iPro = new Intent(getBaseContext(), ProfileActivity.class);
        int monto = Integer.parseInt(etMonto.getText().toString());

        if(monto>0){
            iPro.putExtra(egresos, "Egresos");//Valor ingresado del monto
            iPro.putExtra(ingresos, "Ingresos");//Aun no hecho
            startActivity(iPro);
        }
        else {
            Toast.makeText(TransactionActivity.this, "Ingrese un valor positivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}

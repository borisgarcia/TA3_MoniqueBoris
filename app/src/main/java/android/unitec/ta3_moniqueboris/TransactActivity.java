package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TransactActivity extends AppCompatActivity {
    private String[] lista = {"Selecciones una Opcion","Ingresos","Egresos"};
    EditText etMonto;
    Spinner spinner;
    String opcion;
    int egresos =0, ingresos=0;
    static final String EGRE = "Egresos";
    static final String INGRE = "Ingresos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        etMonto = (EditText)findViewById(R.id.etMonto);
        spinner = (Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,lista);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id){
                if(position == 1)
                    opcion = "ingresos";

                else if(position == 2)
                    opcion = "egresos";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

   public void aplicar(View view) {
       Intent iPro = new Intent(getBaseContext(), ProfileActivity.class);

       int mont = Integer.parseInt(etMonto.getText().toString());

            if (mont > 0) {
                if(opcion.equals("ingresos"))
                    ingresos += mont;

                else if(opcion.equals("egresos"))
                    egresos += mont;

                startActivity(iPro);
                iPro.putExtra(EGRE, egresos+"");
                iPro.putExtra(INGRE, ingresos+"");
            } else {
                Toast.makeText(TransactActivity.this, "Ingrese un valor positivo", Toast.LENGTH_SHORT).show();
            }


    }


    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}

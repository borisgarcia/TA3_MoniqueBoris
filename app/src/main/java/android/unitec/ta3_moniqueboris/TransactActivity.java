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
    static final String INGRESOS = "ingresos";
    static final String EGRESOS = "Egresos";

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
       Intent intent = new Intent ( TransactActivity.this, ProfileActivity.class );
       String error = "";

       int mont = Integer.parseInt(etMonto.getText().toString());
       try {
           if (mont > 0) {
               if (opcion.equals("ingresos"))
                   intent.putExtra (INGRESOS, mont );
               else if (opcion.equals("egresos"))
                   intent.putExtra (EGRESOS,mont );
             startActivity(intent);
           } else {
               Toast.makeText(TransactActivity.this, "Ingrese un valor positivo", Toast.LENGTH_SHORT).show();
           }
       }
       catch(Exception e) {
           error = e.toString();
       }
    }


    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}

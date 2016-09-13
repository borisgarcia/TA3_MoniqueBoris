package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    EditText etEmail, etEgresos, etIngresos, etI_E;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etEmail = (EditText) (findViewById(R.id.editText));
        etIngresos = (EditText) (findViewById(R.id.editText2));
        etEgresos = (EditText) (findViewById(R.id.editText3));
        etI_E = (EditText) (findViewById(R.id.editText4));

        disableEditText(etEmail);
        disableEditText(etIngresos);
        disableEditText(etEgresos);
        disableEditText(etI_E);

        Intent i = getIntent();

        etIngresos.setText(i.getStringExtra(TransactionActivity.ingresos));
        etEgresos.setText(i.getStringExtra(TransactionActivity.egresos));

    }

    private void disableEditText(EditText editText) {
        editText.setEnabled(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    public void Grafica(View view) {
        Intent go = new Intent(getBaseContext(), GraphActivity.class);
        startActivityForResult(go, 0);
    }

    public void Transaction(View view) {
        Intent go2 = new Intent(getBaseContext(), TransactionActivity.class);
        startActivityForResult(go2, 0);
    }
}
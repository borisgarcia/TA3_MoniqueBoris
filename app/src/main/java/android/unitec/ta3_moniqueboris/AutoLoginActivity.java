package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AutoLoginActivity extends AppCompatActivity {

    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login);

        tvEmail = (TextView)findViewById(R.id.tvEmail);
    }


    public void aceptar(View view){
        Intent go = new Intent(getBaseContext(),ProfileActivity.class);
        startActivity(go);
    }

    public void otra(View view){
        Intent go2 = new Intent(getBaseContext(),LoginActivity.class);
        startActivity(go2);
    }
}

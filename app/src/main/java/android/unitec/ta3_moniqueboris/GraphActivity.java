package android.unitec.ta3_moniqueboris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class GraphActivity extends AppCompatActivity {
    GraphView graph;
    TextView etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = (GraphView) findViewById(R.id.graph);
        etEmail = (TextView) findViewById(R.id.txtEmail);

        Intent i = getIntent();
        int ingresos = i.getIntExtra(ProfileActivity.INGRE,0);
        int egresos = i.getIntExtra(ProfileActivity.EGRE,0);
        String email = i.getStringExtra(ProfileActivity.EMAILL);
        etEmail.setText(email);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0,0),
                new DataPoint(1,ingresos),
                new DataPoint(2,0),
                new DataPoint(3,egresos),

        });
        graph.addSeries(series);
        etEmail.setText("Detalle Historico de: "+email);

    }
}

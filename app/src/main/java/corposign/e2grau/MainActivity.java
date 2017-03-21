package corposign.e2grau;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.ParseException;

import corposign.e2grau.R;
import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams
                        .FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void mostraGrafico(View view){
        int a = Integer.parseInt(((EditText) findViewById(R.id.coefA)).getText().toString());
        int b = Integer.parseInt(((EditText) findViewById(R.id.coefB)).getText().toString());
        int c = Integer.parseInt(((EditText) findViewById(R.id.coefC)).getText().toString());

        Intent it = new Intent(MainActivity.this,Activity_grafico.class);

        Bundle bundle = new Bundle();

        bundle.putInt("a", a);
        bundle.putInt("b",b);
        bundle.putInt("c",c);

        it.putExtras(bundle);

        startActivity(it);
    }

    public void resolve(View view) throws ParseException{

        MathView resp = ((MathView) findViewById(R.id.resposta));

        try {
            int a = Integer.parseInt(((EditText) findViewById(R.id.coefA)).getText().toString());
            int b = Integer.parseInt(((EditText) findViewById(R.id.coefB)).getText().toString());
            int c = Integer.parseInt(((EditText) findViewById(R.id.coefC)).getText().toString());

            Equacao2Grau e2 = new Equacao2Grau(a, b, c);
            e2.resolve();

            resp.setText(e2.resposta);

        }catch(Exception e){
            resp.setText("Pelo menos um dos valores digitados não é um número inteiro! Revise suas entradas!");
        }

    }
}

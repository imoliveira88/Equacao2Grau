package corposign.e2grau;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Activity_grafico extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_grafico);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        double a = bundle.getDouble("a");
        double b = bundle.getDouble("b");
        double c = bundle.getDouble("c");

        double xv = (-b/(2*a));
        double yv = -(b*b-4*a*c)/(4*a);

        String vertice = "Coordenada x do vértice da parábola: " + xv + "\n";
        vertice += "Coordenada y do vértice da parábola: " + yv + "\n";

        TextView texto = (TextView) findViewById(R.id.vertice);
        texto.setText(vertice);

        drawChart(a, b, c);
    }

    private void drawChart(double a, double b, double c){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        Equacao2Grau e2 = new Equacao2Grau(a,b,c);
        Double x1,x2;
        e2.resolve();
        if(e2.x1 != null){
            x1 = e2.x1;
            x2 = e2.x2;
        }else{
            x1 = -e2.b/(2*e2.a)-1;
            x2 = -e2.b/(2*e2.a)+2;
        }

        Double passo = (Math.abs(x2-x1))/100;

        Double[] serieX = new Double[100];
        Double[] serieY = new Double[100];

        serieX[0] = x1 - 1;
        serieY[0] = e2.valor(serieX[0]);

        for(int i=1; i<100; i++){
            serieX[i] = serieX[i-1] + passo;
            serieY[i] = e2.valor(serieX[i]);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        for(int i=0; i<100; i++){
            series.appendData(new DataPoint(serieX[i],serieY[i]),false,100);
        }

        series.setTitle("");
        series.setDrawBackground(true);
        series.setColor(Color.GREEN);
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        series.setDrawDataPoints(true);
        series.setThickness(1);
        graph.addSeries(series);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }
}

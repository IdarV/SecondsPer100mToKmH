package idar.ninja.secondsp100mtokmh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import idar.ninja.secondsp100mtokmh.stopwatch.Stopwatch;

public class MainApp extends AppCompatActivity {

    private TextView timerText;
    private TextView lastTimeMilis;
    private Stopwatch stopwatch;
    private Button startStopButton;
    private String time = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        timerText = (TextView) findViewById(R.id.timerText);
        lastTimeMilis = (TextView) findViewById(R.id.lastTime);
        stopwatch = new Stopwatch();
        startStopButton = (Button) findViewById(R.id.startStopButton);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stopwatch.isRunning()){
                    DecimalFormat df = new DecimalFormat("#.##");

                    time = stopwatch.toString();
                    double kmh = 1.0/(stopwatch.getElapsedTimeMili() / 3600.0);
                    lastTimeMilis.setText(df.format(kmh) + " km/h");
                    stopwatch.start();
                } else{
                    stopwatch.start();
                    startStopButton.setText(R.string.lap);
                }
            }
        });
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                timerText.setText(stopwatch.toString());
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }
}

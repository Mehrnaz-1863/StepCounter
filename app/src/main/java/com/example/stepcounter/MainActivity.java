package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvStepCounter,tvSteps;
    SensorManager sensorManager;
    Sensor stepCounter;
   // private boolean SensorCounterPresent;
    int stepCount = 0;
    int step=50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

       // getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        tvStepCounter = findViewById( R.id.tv_stepCounter );
        tvSteps = findViewById( R.id.tv_m );

        sensorManager = (SensorManager) getSystemService( SENSOR_SERVICE );

        if (sensorManager.getDefaultSensor( Sensor.TYPE_STEP_COUNTER ) != null) {
            stepCounter = sensorManager.getDefaultSensor( Sensor.TYPE_STEP_COUNTER );
           // SensorCounterPresent = true;
        } else {
            tvStepCounter.setText( "Counter Sensor is not present" );
           // SensorCounterPresent = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == stepCounter) {
            stepCount = (int) event.values[0];
            tvStepCounter.setText( String.valueOf( stepCount ) );
            Steps();
            if(stepCount==1000){

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor( Sensor.TYPE_STEP_COUNTER ) != null) {
            sensorManager.registerListener( this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL );
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor( Sensor.TYPE_STEP_COUNTER ) != null) {
            sensorManager.unregisterListener( this, stepCounter );
        }


    }
    @SuppressLint("SetTextI18n")
    protected void Steps() {
        if(stepCount==150){
           long result=((long) stepCount *step);
           result=result/100;
           result=result/1000;

            tvSteps.setText( (result+"m"));
        }

    }

}
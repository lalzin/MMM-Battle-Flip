package battle.mmm.battleflipchallenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainBattleFlip extends Activity {
    private TextView mTxtViewX;
    private TextView mTxtViewY;
    private TextView mTxtViewZ;
    private TextView mTxtViewValidator;
    private Float yValue = (float) 0,
                    yPush = (float) 0,
                    yRelease = (float) 0;
    private int multiplicateurBouteille = 1;

    public void position(float iX, float iY, float iZ) {
        String x = "X = " + Float.toString(iX);
        String y = "Y = " + Float.toString(iY);
        String z = "Z = " + Float.toString(iZ);
        mTxtViewX.setText(x);
        mTxtViewY.setText(y);
        mTxtViewZ.setText(z);
    }

    private SensorManager mSensorManager = null;

    private Sensor mAccelerometer = null;


    final SensorEventListener mSensorEventListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

            // Que faire en cas de changement de précision ?

        }


        public void onSensorChanged(SensorEvent sensorEvent) {

            //On affiche la possition en cas de changement

            float[] values = sensorEvent.values;
            yValue = values[1];
            position(values[0], values[1], values[2]);
        }

    };


    @SuppressLint("ClickableViewAccessibility")
    @Override

    public final void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_battle_flip);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mTxtViewX = (TextView) findViewById(R.id.xValueTextActivity);
        mTxtViewY = (TextView) findViewById(R.id.yValueTextActivity);
        mTxtViewZ = (TextView) findViewById(R.id.zValueTextActivity);
        mTxtViewValidator = (TextView) findViewById(R.id.Validator);

        Button launcheur = (Button) findViewById(R.id.launcheur);
        launcheur.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    yPush = yValue;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    yRelease = yValue;
                    if((yPush<(float) -7.5)&& ((yRelease>(float) 7.5) ||
                            (((yRelease.intValue() % 2 == 0) && (yRelease<(float) -11))))){
                        int score = Math.abs(yRelease.intValue() * multiplicateurBouteille)/10;
                        String bravo = "BRAVO push=" + yPush + " release= " + yRelease + "\n Score = " + score;
                        mTxtViewValidator.setText(bravo);
                    }
                    else {
                        String bravo = "NOOONN push=" + yPush + " release= " + yRelease;
                        mTxtViewValidator.setText(bravo);
                    }
                }
                return true;
            }
        });
    }


    @Override

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override

    protected void onPause() {

        super.onPause();

        mSensorManager.unregisterListener(mSensorEventListener, mAccelerometer);

    }



}



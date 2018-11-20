package fr.formation.visionneuse2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    int index = 1;
    ImageView ivImage;
    Sensor sensoraccelero;
    private SensorManager manager;
//    List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImage = findViewById(R.id.imageView2);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensoraccelero = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void recupererimage() {
        Uri adresseimage = Uri.parse("android.resource://fr.formation.visionneuse2/mipmap/carte" + index);
        ivImage.setImageURI(adresseimage);

    }

    public void scrollright(View view) {
        index++;
        if (index > 5) {
            index = 0;
        }
        recupererimage();


    }
    public void scrollrightshake() {
        index++;
        if (index > 5) {
            index = 0;
        }
        recupererimage();


    }

    public void scrollleft(View view) {
        index--;
        if (index < 0) {
            index = 5;
        }
        recupererimage();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightValue;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            lightValue = event.values[0];
            if(lightValue>14){
                scrollrightshake();
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, sensoraccelero, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }


}

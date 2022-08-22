package com.ctss.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

@NativePlugin
public class Sensors extends Plugin implements SensorEventListener {

    private AppCompatActivity activity;

    private SensorManager sensorManager;
    private Sensor magnetometer;
    private Sensor accelerometer;
    private Sensor gyroscope;

    @Override
    public void load() {
        this.activity = getActivity();
        initSensors(this.activity);
    }

    private void initSensors(AppCompatActivity activity) {
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        registerAvailableSensors();
        
    }

    private void registerAvailableSensors() {
        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        JSObject ret = getJSObjectForSensorData(sensorEvent);
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            notifyListeners("accelerometerChange", ret);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            notifyListeners("gyroscopeChange", ret);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            notifyListeners("magnetometerChange", ret);
        }
    }

    private JSObject getJSObjectForSensorData(SensorEvent event) {
        JSObject res = new JSObject();
        res.put("x", event.values[0]);
        res.put("y", event.values[1]);
        res.put("z", event.values[2]);
        return res;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

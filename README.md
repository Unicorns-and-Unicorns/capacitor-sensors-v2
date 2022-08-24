# How to use

### First build the plugin
```
npm install
npm run build
```
### In the ionic project install it like this (just change path to yours)
```
npm install @unicorns-and-unicorns/capacitor-sensors-v2 --save
npx cap sync
```
### And then
```
ionic capacitor sync android
ionic capacitor sync ios
```

### In your Ionic Android project, add this code, to make to make Capacitor aware of the plugins
```
public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      add(Sensors.class);
    }});
  }
}
```

### You can use the Sensors like this
```
import { Plugins } from '@capacitor/core';
import { SensorData, Sensors } from 'sensors';

useEffect(() => {
		Plugins.Sensors.addListener('magnetometerChange', (res: SensorData) => {
			setMangetometerX(res.x);
			setMangetometerY(res.y);
			setMangetometerZ(res.z);
		});
		Plugins.Sensors.addListener('gyroscopeChange', (res: SensorData) => {
			setGyroscopeX(res.x);
			setGyroscopeY(res.y);
			setGyroscopeZ(res.z);
		});
		Plugins.Sensors.addListener('accelerometerChange', (res: SensorData) => {
			setAccelerometerX(res.x);
			setAccelerometerY(res.y);
			setAccelerometerZ(res.z);
		});
	}, []);
```
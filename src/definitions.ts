import { PluginListenerHandle } from '@capacitor/core';

declare module '@capacitor/core' {
  interface PluginRegistry {
    Sensors: SensorsPlugin;
  }
}

export interface SensorsPlugin {
  /**
   * Listens for magnetometer data change.
   */
  addListener(
    eventName: 'magnetometerChange',
    listenerFunc: (magnetometerData: SensorData) => void,
  ): PluginListenerHandle;
  /**
   * Listens for accelerometer data change.
   */
  addListener(
    eventName: 'accelerometerChange',
    listenerFunc: (magnetometerData: SensorData) => void,
  ): PluginListenerHandle;
  /**
   * Listens for gyroscope data change.
   */
  addListener(
    eventName: 'gyroscopeChange',
    listenerFunc: (magnetometerData: SensorData) => void,
  ): PluginListenerHandle;
  /**
   * Removes all listeners
   */
  removeAllListeners(): void;
}

export interface SensorData {
  x: number;
  y: number;
  z: number;
}

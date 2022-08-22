import Foundation
import Capacitor
import Combine
import CoreMotion
import SwiftUI

@objc(Sensors)
public class Sensors: CAPPlugin {
    private var motionManager: CMMotionManager?
    
    override public func load() {
        self.motionManager = CMMotionManager()
        updateMagnetometerData()
        updateAccelerometerData()
        updateGyroscopeData()
    }
    
    func updateMagnetometerData() {
        if (!self.motionManager!.isMagnetometerAvailable){
            return;
        }
        self.motionManager!.magnetometerUpdateInterval = 1
        self.motionManager!.startMagnetometerUpdates(to: .main) { (magnetometerData, error) in
            guard error == nil else {
                print(error!)
                return
            }
            
            if let magnetData = magnetometerData {
                self.notifyListeners("magnetometerChange", data: ["x": magnetData.magneticField.x, "y": magnetData.magneticField.y, "z": magnetData.magneticField.z] )
            }
        }
    }
    
    func updateAccelerometerData() {
        if (!self.motionManager!.isAccelerometerAvailable){
            return;
        }
        self.motionManager!.accelerometerUpdateInterval = 1
        self.motionManager!.startAccelerometerUpdates(to: .main) { (accelerometerData, error) in
            guard error == nil else {
                print(error!)
                return
            }
            
            if let aData = accelerometerData {
                self.notifyListeners("accelerometerChange", data: ["x": aData.acceleration.x, "y": aData.acceleration.y, "z": aData.acceleration.z] )
            }
        }
    }
    
    func updateGyroscopeData() {
        if (!self.motionManager!.isGyroAvailable){
            return;
        }
        self.motionManager!.gyroUpdateInterval = 1
        self.motionManager!.startGyroUpdates(to: .main) { (gyroData, error) in
            guard error == nil else {
                print(error!)
                return
            }
            
            if let gData = gyroData {
                self.notifyListeners("gyroscopeChange", data: ["x": gData.rotationRate.x, "y": gData.rotationRate.y, "z": gData.rotationRate.z] )
            }
        }
    }
}

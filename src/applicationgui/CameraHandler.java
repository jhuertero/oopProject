/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;



import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author ibm-lenovo
 */
public class CameraHandler {
    
    List <Camera> cameras;

    public CameraHandler() {
        cameras = new ArrayList<>();
    }
    
  public boolean addCamera (String id, String deviceName, String serialNumber, String condition, Device.deviceType type, String cameraType, String megapixel, String storageCapacity, String storageType){ 
      for (Camera camera: cameras) {
            if (camera.getId().equals(id)) {
                return false;
            }
        }
     Camera d = new Camera ( id, deviceName, serialNumber, condition, type, cameraType, megapixel, storageCapacity, storageType);
      cameras.add(d);
      return true;
      
  }
    
  public Camera getCamera(String id){
        Camera d = new Camera ();
        for (Camera camera : cameras) {
            if (camera .getId().equals(id)) {
                d.setId(camera .getId());
                d.setDeviceName(camera .getDeviceName());
                d.setSerialNumber(camera .getSerialNumber());
                d.setCondition(camera .getCondition());
                d.setType(camera .getType());
                d.setCameraType(camera.getCameraType());
                d.setMegaPixel(camera.getMegaPixel());
                d.setStorageCapacity(camera.getStorageCapacity());
                d.setStorageType(camera.getStorageType());
            }
        }
        return d;
    }

  public void updateCamera(String id, String deviceName, String serialNumber, String condition, Device.deviceType deviceType,
           String cameraType, String megaPixel, String storageCapacity, String storageType){
        for (Camera camera : cameras) {
            if (camera.getId().equals(id)) {
                camera.setDeviceName(deviceName);
                camera.setSerialNumber(serialNumber);
                camera.setCondition(condition);
                camera.setType(deviceType);
                camera.setCameraType(cameraType);
                camera.setMegaPixel(megaPixel);
                camera.setStorageCapacity(storageCapacity);
                camera.setStorageType(storageType);
            }
        }
    }

  public void deleteCamera(String id){
        Camera d;
        for (Camera camera: cameras) {
            if(camera.getId().equals(id)){
                 d = camera;
                 cameras.remove(d);
                 System.out.println("Camera removed");
            }
        }
    }
}

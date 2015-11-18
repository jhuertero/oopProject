/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    
  public boolean addCamera (Camera c){ 
      for (Camera camera: cameras) {
            if (camera.getId().equals(c.getId())) {
                return false;
            }
        }
      cameras.add(c);
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
  
  public void SerializeCamera() {
        try {
            FileOutputStream fileOut = new FileOutputStream("cameras.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cameras);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in cameras.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
	
    public void DeserializeCameraList( ) {
        try {
            FileInputStream fileIn = new FileInputStream("cameras.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cameras = (List<Camera>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Camera class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized cameras.ser Contents ...");
        for(Camera e : cameras )
            System.out.println(e.toString());        
    }
}

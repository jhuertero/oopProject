/**
 *
 * @author Jose
 */
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class DeviceHandler {
    
    List <Device> devices;

    public DeviceHandler() {
        devices = new ArrayList<>();
    }
    
  public boolean addDevice (Device d){ 
    for (Device dv: devices) {
        if (dv.getId().equals(d.getId())) {
            return false;
        }
    }
    devices.add(d);
    return true;      
  }
    
  public Device getDevices(String id){
        Device d;
        for(int i = 0; i < devices.size(); i++){
            if(devices.get(i).getId().equals(id)){
                d = devices.get(i);
                return d;
            }
        }
        d = new Device();
        return d;
    }

  public void updateDevice(Device d){
        Device p;
        for(int i = 0; i < devices.size(); i++){
            if(devices.get(i).getId().equals(d.getId())){
                 p = devices.get(i);
                 devices.remove(p);
                 devices.add(d);
                 System.out.println("person removed");
            }
        }
    }

  public void deleteDevice(String id){
        Device p;
        for(int i = 0; i < devices.size(); i++){
            if(devices.get(i).getId().equals(id)){
                 p = devices.get(i);
                 devices.remove(p);
                 System.out.println("person removed");
            }
        }
    }
  
  public void SerializeDevice() {
        try {
            FileOutputStream fileOut = new FileOutputStream("devices.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(devices);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in devices.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
	
	public void DeserializeDeviceList( ) {
        try {
            FileInputStream fileIn = new FileInputStream("devices.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            devices = (List<Device>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Device class not found");
            c.printStackTrace();
            return;
        }
        
        System.out.println("Deserialized devices.ser Contents ...");
        for(Device d : devices )
            System.out.println(d.toString());        
    }
}


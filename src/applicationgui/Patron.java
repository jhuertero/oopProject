/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EPALLARE
 */
public class Patron extends Person {
    
     List <String> devices;
    
    public Patron() {}
    public Patron(String id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
        
        devices = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    
    public boolean addDevice (String device_id){ 
    for (String dv: devices) {
        if (dv.equals(device_id)) {
            return false;
        }
    }
    devices.add(device_id);
    return true;      
  }
      
     public boolean deleteDevice(String device_id){
    for (String dv: devices) {
        if (dv.equals(device_id)) {
            return false;
        }
    }
    devices.remove(device_id);
    return true;      
  }
    
     
      public void updatePerson(String device_id){
          
       if(addDevice(device_id)) 
       {System.out.println("Device checkedout completed");}
       else
       {System.out.println("Device checkedout cannot be completed");}
      
       
    }
     
}

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
    
    
     
      public void checkoutDevice(String device_id){
          
        for (String dv: devices) {
        if (dv.equals(device_id)) {
         System.out.println("Device is already checkedout to this user !");
         }else{
           devices.add(device_id);
           }}}
      
      public void checkinDevice(String device_id){
          
        for (String dv: devices) {
        if (dv.equals(device_id)) {
             devices.remove(device_id);
         }else{
           System.out.println("Device cannot be checkedin !");
           }}}
}
     


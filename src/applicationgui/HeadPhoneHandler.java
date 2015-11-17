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
 * @author Jose
 */
public class HeadPhoneHandler {
    
    List <Headphones> headphones;

    public HeadPhoneHandler() {
        headphones = new ArrayList<>();
    }
    
  public boolean addHeadPhones (String id, String deviceName, String serialNumber, String condition, Device.deviceType type, String headphonesType, boolean hasMic, boolean hasVolumeControl, int cordLength, double plugDiameter){ 
    for (Headphones headphone: headphones) {
        if (headphone.getId().equals(id)) {
            return false;
        }
    }
    Headphones h = new Headphones (id, deviceName, serialNumber, condition, type, headphonesType, hasMic, hasVolumeControl, cordLength, plugDiameter);
    headphones.add(h);
    return true;
      
  }
    
  public Headphones getHeadphones(String id){
        Headphones h = new Headphones ();
        for (Headphones headphone : headphones) {
            if (headphone .getId().equals(id)) {
                h.setId(headphone .getId());
                h.setDeviceName(headphone .getDeviceName());
                h.setSerialNumber(headphone .getSerialNumber());
                h.setCondition(headphone .getCondition());
                h.setType(headphone.getType());
                h.setHeadphonesType(headphone.getHeadphonesType());
                h.setHasMic(headphone.isHasMic());
                h.setHasVolumeControl(headphone.isHasVolumeControl());
                h.setCordLength(headphone.getCordLength());
                h.setPlugDiameter(headphone.getPlugDiameter());
            }
        }
        return h;
    }

  public void updateHeadphones(String id, String deviceName, String serialNumber, String condition, Device.deviceType type, String headphonesType, boolean hasMic, boolean hasVolumeControl, int cordLength, double plugDiameter){
        for (Headphones headphones : headphones) {
            if (headphones.getId().equals(id)) {
                headphones.setDeviceName(deviceName);
                headphones.setSerialNumber(serialNumber);
                headphones.setCondition(condition);
                headphones.setType(type);
                headphones.setHeadphonesType(headphonesType);
                headphones.setHasMic(hasMic);
                headphones.setHasVolumeControl(hasVolumeControl);
                headphones.setCordLength(cordLength);
                headphones.setPlugDiameter(plugDiameter);
            }
        }
    }

  public void deleteHeadphones(String id){
        Headphones h;
        for (Headphones headphone: headphones) {
            if(headphone.getId().equals(id)){
                 h = headphone;
                 headphones.remove(h);
                 System.out.println("Camera removed");
            }
        }
    }
}


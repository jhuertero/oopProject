/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;


/**
 *
 * @author ibm-lenovo
 */



public class Device implements java.io.Serializable{
    
    public enum deviceType {
    computer, laptop, headphones, camera
}
    
    
    private String id;
    private String deviceName;
    private String serialNumber;
    private String condition;
    private deviceType type;

    public Device() {
    }
    
    public Device(String id, String deviceName, String serialNumber, String condition, deviceType type) {
        this.id = id;
        this.deviceName = deviceName;
        this.serialNumber = serialNumber;
        this.condition = condition;
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setType(deviceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getCondition() {
        return condition;
    }

    public deviceType getType() {
        return type;
    }
    
    public String toString(){
        return id + " " + deviceName + " " + serialNumber + " " + condition + " " + type;
    }
}

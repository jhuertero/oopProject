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
public class Headphones extends Device {
    
    private String headphonesType;
    private boolean hasMic;
    private boolean hasVolumeControl;
    private int cordLength;
    private double plugDiameter;

    public Headphones() {
    }

    public Headphones(String id, String deviceName, String serialNumber, String condition, Device.deviceType type, String headphonesType, boolean hasMic, boolean hasVolumeControl, int cordLength, double plugDiameter) {
        super(id, deviceName, serialNumber, condition, type);
        this.headphonesType = headphonesType;
        this.hasMic = hasMic;
        this.hasVolumeControl = hasVolumeControl;
        this.cordLength = cordLength;
        this.plugDiameter = plugDiameter;
    }

    public void setHeadphonesType(String type) {
        this.headphonesType = type;
    }

    public void setHasMic(boolean hasMic) {
        this.hasMic = hasMic;
    }

    public void setHasVolumeControl(boolean hasVolumeControl) {
        this.hasVolumeControl = hasVolumeControl;
    }

    public void setCordLength(int cordLength) {
        this.cordLength = cordLength;
    }

    public void setPlugDiameter(double plugDiameter) {
        this.plugDiameter = plugDiameter;
    }

    public String getHeadphonesType() {
        return headphonesType;
    }

    public boolean isHasMic() {
        return hasMic;
    }

    public boolean isHasVolumeControl() {
        return hasVolumeControl;
    }

    public int getCordLength() {
        return cordLength;
    }

    public double getPlugDiameter() {
        return plugDiameter;
    }
    
    public String toString(){
        return super.toString() + " " +headphonesType + " " +hasMic + " " +hasVolumeControl + " " +cordLength +" " +plugDiameter;
    }
    
}

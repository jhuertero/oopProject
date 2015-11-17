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
public class Camera extends Device {
    
    private String cameraType;
    private String megaPixel;
    private String storageCapacity;
    private String storageType;

    public Camera() {
    }

    public Camera(String id, String deviceName, String serialNumber, String condition, Device.deviceType type, String cameraType, String megaPixel, String storageCapacity, String storageType) {
        super(id, deviceName, serialNumber, condition, type);
        this.cameraType = cameraType;
        this.megaPixel = megaPixel;
        this.storageCapacity = storageCapacity;
        this.storageType = storageType;
    }

    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    public void setMegaPixel(String megaPixel) {
        this.megaPixel = megaPixel;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getCameraType() {
        return cameraType;
    }

    public String getMegaPixel() {
        return megaPixel;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public String getStorageType() {
        return storageType;
    }
    
    public String toString(){
        return super.toString() + " " +cameraType + " " +megaPixel + " " +storageCapacity + " " +storageType;
    }
}

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
public class Computer extends Device {
    
    private String CPU;
    private String RAM;
    private String diskSize;

    public Computer() {
    }

    /*public Computer(String id) {
        super(id);
    }*/

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getDiskSize() {
        return diskSize;
    }
    
    
}

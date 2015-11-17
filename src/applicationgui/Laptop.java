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
public class Laptop extends Computer {
    
    private String screenSize;
    private boolean isTouch;

    public Laptop() {
    }

    /*public Laptop(String id) {
        super(id);
    }*/

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public void setIsTouch(boolean isTouch) {
        this.isTouch = isTouch;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public boolean isIsTouch() {
        return isTouch;
    }
    
    
}

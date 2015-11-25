/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;


/**
 *
 * @author Jose
 */
public class Person implements java.io.Serializable{

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    
    public Person(){}
    public Person(String id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    public String getID(){
        return id;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    @Override
    public String toString(){
        return id + " " + firstName + " " + lastName + " " + email;
    }
    
    public void checkoutDevice(String device_id){
        
    }
      public void checkinDevice(String device_id){
        
    }
}

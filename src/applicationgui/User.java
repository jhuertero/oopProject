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
public class User extends Patron {
    static public enum UserType{SUPERUSER, USER};
    private String password;
    private UserType type;
    private boolean authenticationStatus;
    
    public User(){}
    
    public User(String id, String firstName, String lastName, String email, String password, UserType type){
        super(id, firstName, lastName, email);
        this.password = password;
        this.type = type;
    }
    
    public void setUserType(UserType type){
        this.type = type;
    }
    
    public UserType getUserType(){
        return type;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setAuthenticationStatus(boolean authenticationStatus){
        this.authenticationStatus = authenticationStatus;
    }
    
    public boolean getAuthenticationStatus(){
        return authenticationStatus;
    }
    
    public boolean isAuthenticated(){
        return authenticationStatus;
    }
    
    public String toString(){
        return super.toString() + " " + password + " " + type + " " + authenticationStatus;
    }
}

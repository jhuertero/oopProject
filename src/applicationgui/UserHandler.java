/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;


import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 *
 * @author Jose
 */
public class UserHandler {
    List<User> users; 
    
    public UserHandler(){
        users = new ArrayList<>();
    }
    
    public boolean addUser(String id, String firstName, String lastName, String email, String password, User.UserType type){
        for (User user : users) {
            if (user.getID().equals(id)) {
                return false;
            }
        }
        User u = new User(id, firstName, lastName, email, password, type);
        users.add(u);
        return true;
    }
    
    public User getUser(String id){
        User p = new User();
        for (User user : users) {
            if (user.getID().equals(id)) {
                p.setID(user.getID());
                p.setFirstName(user.getFirstName());
                p.setLastName(user.getLastName());
                p.setEmail(user.getEmail());
                p.setUserType(user.getUserType());
                p.setPassword(user.getPassword());
                p.setAuthenticationStatus(user.getAuthenticationStatus());
            }
        }
        return p;
    }
    
    public void updateUser(String id, String firstName, String lastName, String email, String password, User.UserType type, boolean authenticationStatus){
        for (User user : users) {
            if (user.getID().equals(id)) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(type);
                user.setAuthenticationStatus(authenticationStatus);
            }
        }
    }
    
    public boolean deleteUser(String id){
        User p;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getID().equals(id)){
                 p = users.get(i);
                 users.remove(p);
                 System.out.println("User removed");
                 return true;
            }
        }
        return false;
    }
    
    public void SerializeUser() {
        try {
            FileOutputStream fileOut = new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in users.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
	
	public void DeserializeUserList( ) {
        try {
            FileInputStream fileIn = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (List<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized users.ser Contents ...");
        for(User e : users )
            System.out.println(e.toString());        
    }
}
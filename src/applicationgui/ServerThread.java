/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Jose
 */

//TO-DO add update device, check in and check out socket functions
public class ServerThread extends Thread{
    private final Socket socket;
    DeviceHandler dh;
    PersonHandler ph;
    
    public ServerThread(Socket socket, PersonHandler ph, DeviceHandler dh){
        this.socket = socket;
        this.dh = dh;
        this.ph = ph;
    }
    
    public void run() {
        try {
            String action, message;
            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            message = action = (String) input.readObject();
            System.out.println(message);

            if (isValidAction(action)) {
                message = "OK";
                output.writeObject(message);

                switch (action) {
                    case "login":
                        Login(socket);
                        break;
                    case "deleteUser":
                        removePerson(socket);
                        break;
                    case "addPerson":
                        addUser(socket);
                        break;
                    case "addDevice":
                        addDevice(socket);
                        break;
                    case "removeDevice":
                        removeDevice(socket);
                        break;
                    case "getDevice":
                        getDevice(socket);
                        break;
                    case "updateDevice":
                        updateDevice(socket);
                        break;
                    case "checkoutDevice":
                        checkoutDevice(socket);
                        break;
                    case "checkinDevice":
                        checkinDevice(socket);
                        break;
                }
                System.out.println(message);
            }
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private boolean isValidAction(String action) {
        final String[] validActions = {
            "login", 
            "deleteUser", 
            "addPerson", 
            "addDevice", 
            "removeDevice", 
            "getDevice", 
            "updateDevice",
            "checkoutDevice",
            "checkinDevice"
        };
        boolean isValid = false;
        for (String validAction : validActions) {
            if (action.equals(validAction)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
    
    private synchronized void Login(Socket socket) {
        String response = "-1"; // default response to "error"
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
                User loginUser = (User) input.readObject();
                if (loginUser != null) {
                    try {
                        User existingUser = (User) ph.getPerson(loginUser.getID());
                        if (existingUser != null) {
                            if (existingUser.getPassword().equals(loginUser.getPassword())) {
                                // password matches; check UserType
                                if (existingUser.getUserType() == User.UserType.SUPERUSER) {
                                    response = "1";
                                } else {
                                    response = "2";
                                }
                            } // otherwise the user password does not match
                        } // otherwise the user is an invalid user
                    } catch (NullPointerException e) {
                        System.err.println("Error: " + e.getMessage());
                        e.printStackTrace(System.err);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace(System.err);

            } finally {
                output.writeObject(response);
                input.close();
                output.close();
            }
        } catch (Exception e) {
            //TO-DO might need to add the -1 section here
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }    
    
    private synchronized void removePerson(Socket socket) {
        String response = "-1"; // default response to "error"
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
                String id = (String) input.readObject();
                Person person = ph.getPerson(id);
                if (ph.deletePerson(person.getID())) {
                    // the person was successfully deleted (from in-memory list)
                    response = "1";
                    ph.SerializePerson(); // commit change
                } // otherwise the person was not deleted
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace(System.err);
            } finally {
                output.writeObject(response);
                input.close();
                output.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private synchronized void addUser(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Person p = (Person)ois.readObject();
            
            if(ph.addPerson(p) == true){
                String message = "1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                ph.SerializePerson();
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private synchronized void addDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Device c = (Device)ois.readObject();
            
            if(dh.addDevice(c) == true){
                String message = "1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                dh.SerializeDevice();
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private synchronized void removeDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String id = (String)ois.readObject();
            Device d = dh.getDevice(id);
            if(dh.deleteDevice(d.getId()) == true){
                String message = "1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                dh.SerializeDevice();
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private synchronized void getDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String id = (String)ois.readObject();
            Device d = dh.getDevice(id);
            if(d != null){
                String message = "1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                
                os = s.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(d);
                
                os.close();
                dh.SerializeDevice();
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close(); 
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private synchronized void updateDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Device c = (Device)ois.readObject();
            
            if(dh.updateDevice(c) == true){
                String message = "1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                dh.SerializeDevice();
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    
    
    private synchronized void checkoutDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String patron_id = (String)ois.readObject();
            String device_id = (String)ois.readObject();
            
            Patron p = (Patron)ph.getPerson(patron_id);
            Device d = dh.getDevice(device_id);
            
            if(p != null && d != null){
                if(dh.isCheckedOut(d) == false){
                    p.checkoutDevice(d.getId());
                    d.setCheckedOutTo(p.getID());
                    String message = "1";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    os.close();
                    ph.SerializePerson();
                    dh.SerializeDevice();
                }else{
                    String message = "2";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    os.close();
                }
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
      private synchronized void checkinDevice(Socket s){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String device_id = (String)ois.readObject();
            Device d = dh.getDevice(device_id);
            
            if(d != null){
                if(dh.isCheckedOut(d) == true){
                    Patron p = (Patron)ph.getPerson(d.getCheckedOutTo());
                    p.checkinDevice(d.getId());
                    d.setCheckedOutTo(null);
                    String message = "1";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    os.close();
                    ph.SerializePerson();
                    dh.SerializeDevice();
                }else{
                    String message = "2";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    os.close();
                    ph.SerializePerson();
                }
            }else{
                String message = "-1";
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(message);
                os.close();
                
            }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
}

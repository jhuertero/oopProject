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
                        Login(socket, ph);
                        break;
                    case "deleteUser":
                        removePerson(socket, ph);
                        break;
                    case "addPerson":
                        addUser(socket, ph);
                        break;
                    case "addDevice":
                        addDevice(socket, dh);
                        break;
                    case "removeDevice":
                        removeDevice(socket, dh);
                        break;
                    case "getDevice":
                        getDevice(socket, dh);
                        break;
                    case "updateDevice":
                        updateDevice(socket, dh);
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
            "udpateDevice"
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
    
    private synchronized void Login(Socket s, PersonHandler ph){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            User p = (User)ois.readObject();            
            if(p != null){
                try{
                    User u1;
                    u1 = (User)(ph.getPerson(p.getID()));
                    if(u1.getPassword().equals(p.getPassword()) == true){
                        String message;
                                    
                        if(u1.getUserType() == User.UserType.SUPERUSER)
                            message = "1";
                        else
                            message = "2";

                        OutputStream os = s.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);
                        oos.writeObject(message);
                        os.close();
                    }else{
                        String message = "-1";
                        OutputStream os = s.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);
                        oos.writeObject(message);
                        os.close();
                    }
                }catch (NullPointerException npe){
                    String message = "-1";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    os.close();
                }
            }
        }catch(Exception e){
            //TO-DO might need to add the -1 section here
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private synchronized void removePerson(Socket s, PersonHandler ph){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String id = (String)ois.readObject();
            Person d = ph.getPerson(id);
            if(ph.deletePerson(d.getID()) == true){
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
    
    private synchronized void addUser(Socket s, PersonHandler ph){
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
    
    private synchronized void addDevice(Socket s, DeviceHandler dh){
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
    
    private synchronized void removeDevice(Socket s, DeviceHandler dh){
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
    
    private synchronized void getDevice(Socket s, DeviceHandler dh){
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

  private synchronized void updateDevice(Socket s, DeviceHandler dh){
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




}

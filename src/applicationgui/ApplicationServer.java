/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;
import java.net.ServerSocket;  // The server uses this to bind to a port
import java.net.Socket;        // Incoming connections are represented as sockets
import java.io.*;
/**
 *
 * @author Jose
 */
public class ApplicationServer {

    /**
     */
    
    public static final int SERVER_PORT = 8765;
    public static void main(String[] args) {
        /*UserHandler uh = new UserHandler();
        DeviceHandler dh = new DeviceHandler();
        PersonHandler ph = new PersonHandler();
        
        ph.DeserializePersonList();
        uh.DeserializeUserList();
        dh.DeserializeDeviceList();
        
        while(true){
            try{
                String message;
                final ServerSocket ss = new ServerSocket(SERVER_PORT);
                
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                message = (String)ois.readObject();
                System.out.println(message);
                
                if(message.equals("login") == true){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    Login(s, ph);
                    oos.close();
                    os.close();
                }else if(message.equals("deleteUser")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    removeUser(s, uh);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("addUser")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    addUser(s, ph);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("addDevice")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    addDevice(s, dh);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("removeDevice")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    removeDevice(s, dh);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("getDevice")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    getDevice(s, dh);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }
                ois.close();
                is.close();
                s.close();
                ss.close();
                
            }catch(Exception e){
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace(System.err);
            }
        }*/
        DeviceHandler dh = new DeviceHandler();
        PersonHandler ph = new PersonHandler();
        
        ph.DeserializePersonList();
        dh.DeserializeDeviceList();
        try{
        final ServerSocket ss = new ServerSocket(SERVER_PORT);
        Socket sock = null;
        ServerThread thread = null;
        while(true){
            sock = ss.accept();
            thread = new ServerThread(sock, ph, dh);
            thread.start();
        }
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

/*    
    private static void Login(Socket s, PersonHandler ph){
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
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private static void removeUser(Socket s, UserHandler uh){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            User u = (User)ois.readObject();
            System.out.println(u.getID());
            if(uh.deleteUser(u.getID()) == true){
                String message = "1";
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
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private static void addUser(Socket s, PersonHandler ph){
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
    
    private static void addDevice(Socket s, DeviceHandler dh){
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
    
    private static void removeDevice(Socket s, DeviceHandler dh){
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
    
    private static void getDevice(Socket s, DeviceHandler dh){
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
*/
}

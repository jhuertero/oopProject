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
     * @param args the command line arguments
     */
    
    public static final int SERVER_PORT = 8765;
    public static void main(String[] args) {
        UserHandler uh = new UserHandler();
        CameraHandler ch = new CameraHandler();
        HeadPhoneHandler hh = new HeadPhoneHandler();
        PersonHandler ph = new PersonHandler();
        
        ph.DeserializeUserList();
        uh.DeserializeUserList();
        ch.DeserializeCameraList();
        RequestHandler rh;
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
                }else if(message.equals("addCamera")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    addCamera(s, ch);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("addHeadphones")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    addHeadphones(s, hh);
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
        }
    }
    
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
                ph.SerializeUser();
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
    
    private static void addCamera(Socket s, CameraHandler ch){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Camera c = (Camera)ois.readObject();
            
            if(ch.addCamera(c) == true){
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
    
    private static void addHeadphones(Socket s, HeadPhoneHandler hh){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Headphones hp = (Headphones)ois.readObject();
            
            if(hh.addHeadPhones(hp) == true){
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
}

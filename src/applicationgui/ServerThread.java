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
    private final Socket s;
    DeviceHandler dh;
    PersonHandler ph;
    
    public ServerThread(Socket socket, PersonHandler ph, DeviceHandler dh){
        this.s = socket;
        this.dh = dh;
        this.ph = ph;
    }
    
    public void run(){
        try{
        String message;
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
                    removePerson(s, ph);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }else if(message.equals("addPerson")){
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
                }else if(message.equals("updateDevice")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    updateDevice(s, dh);
                    System.out.println(message);
                    oos.close();
                    os.close();
                }
                ois.close();
                is.close();
                s.close();
                
                
            }catch(Exception e){
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace(System.err);
            }
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

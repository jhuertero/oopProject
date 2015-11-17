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
        uh.DeserializeUserList();
        
        while(true){
            try{
                String message;
                final ServerSocket ss = new ServerSocket(SERVER_PORT);
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                message = (String)ois.readObject();
                
                if(message.equals("login") == true){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
                    Login(s, uh);
                    oos.close();
                    os.close();
                }else if(message.equals("addUser")){
                    message = "OK";
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message);
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
    
    private static void Login(Socket s, UserHandler uh){
        try{
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            User u = (User)ois.readObject();            
            if(u != null){
                try{
                    User u1;
                    u1 = uh.getUser(u.getID());
                    if(u1.getPassword().equals(u.getPassword()) == true){
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;

/**
 *
 * @author EPALLARE
 */
public class Patron extends Person {
    
    public Patron() {}
    public Patron(String id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}

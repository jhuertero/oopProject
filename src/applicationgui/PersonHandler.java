/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationgui;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Jose
 */
public class PersonHandler {
    List<Person> persons = new ArrayList<Person>(); 
    
    public PersonHandler(){
        persons = new ArrayList<Person>();
    }
    
    public void addPerson(String id, String firstName, String lastName, String email){
        Person p = new Person(id, firstName, lastName, email);
        persons.add(p);
    }
    
    public Person getPerson(String id){
        Person p = new Person();
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getID().equals(id)){
                p.setID(persons.get(i).getID());
                p.setFirstName( persons.get(i).getFirstName());
                p.setLastName( persons.get(i).getLastName());
                p.setEmail( persons.get(i).getEmail());
            }
        }
        return p;
    }
    
    public void updatePerson(String id, String firstName, String lastName, String email){
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getID().equals(id)){
                persons.get(i).setFirstName(firstName);
                persons.get(i).setLastName(lastName);
                persons.get(i).setEmail(email);
            }
        }
    }
    
    public void deletePerson(String id){
        Person p;
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getID().equals(id)){
                 p = persons.get(i);
                 persons.remove(p);
                 System.out.println("person removed");
            }
        }
    }
}

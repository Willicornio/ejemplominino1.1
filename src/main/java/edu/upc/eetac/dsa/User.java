package edu.upc.eetac.dsa;
import java.util.LinkedList;

public class User {
    String nombre;
    String surname;
    String idUser;
    LinkedList<Bike> listaBike;

    public User (String idUser,String nombre, String surname){
        this.nombre = nombre;
        this.surname = surname;
        this.idUser = idUser;
        this.listaBike = new LinkedList<Bike>();
    }
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSurname(){
        return nombre;
    }

    public void setSurname(String nombre) {
        this.nombre = nombre;
    }

    public void addBike ( Bike b){
        this.listaBike.add(b);
    }
    public LinkedList<Bike> consultaBike(){

        return this.listaBike;

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}

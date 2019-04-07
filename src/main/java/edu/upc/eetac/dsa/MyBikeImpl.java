package edu.upc.eetac.dsa;
import java.util.List;
import java.util.*;
import org.apache.log4j.Logger;

public class MyBikeImpl implements MyBike {
    final static Logger log = Logger.getLogger(MyBikeImpl.class.getName());
public static MyBike instance;
    private Station stations[];
    private LinkedList<Bike> bikes;
    private HashMap<String,User> users;
    private LinkedList<Bike> listaBikes;
    int numstations;

    public MyBikeImpl(){

        stations = new Station[S];
        numstations=0;
        listaBikes = new LinkedList<Bike>();
        bikes = new LinkedList<Bike>();
        users = new HashMap<String, User>();
    }
    public static MyBike getInstance(){

        if(instance == null) instance = new MyBikeImpl();

        return instance;

    }

    public void addUser(String idUser,String name, String surname){
        log.info("Añadiendo user");
        this.users.put(idUser, new User(idUser,name, surname));
    }


    public void addStation(String idStation, String description, int max, double lat, double lon){
        log.info("Añadiendo stacion");
        Station sta = new Station(idStation,description,max,lat,lon);
        this.stations[this.numstations] = sta;
        this.numstations++;

    }
    /*
    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException{

        log.info("Bike añadida:" +idBike +description +kms +idStation);

        for (int i=0; i<stations.size(); i++){

            if (idStation.equals(this.stations.get(i).getidStation())){

                this.addBike(new Bike(idBike, description, kms, idStation));

            }

        }
*/
    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException {
        log.info("Añadiendo bike");
        Station sta = null;
        Bike bike;

        for (int i = 0; i < this.numstations; i++) {
            if (idStation.equals(this.stations[i].idStation)) {
                sta = this.stations[i];
            }
        }

        log.info("Stacion" + sta);

        if (sta != null) {
            LinkedList<Bike> bikes = sta.consultaBikes();
            int maxbikes = sta.getMax();
            if (150 > sta.getMax()) {
                sta.addBike(new Bike(idBike, description, kms, idStation));

            } else {
                log.error("Sta full");
                throw new StationFullException();

            }
        }
        else{
            log.error("Sta notfound");
            throw new StationNotFoundException();
        }
    }
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException {
    List<Bike> lista = new ArrayList<Bike>(this.bikes);
    Station sta = null;
        for(int i=0; i<this.numstations; i++){

            if(idStation.equals(this.stations[i].idStation)){

                sta = this.stations[i];

            }

        }

        log.info("Sta :" +sta);
        if ( sta != null) {
            Collections.sort(lista, new Comparator<Bike>() {
                public int compare(Bike b1, Bike b2) {
                    return (int) (b1.getKms() - b2.getKms());
                }
            });
        }
            else {
                log.error("Sta no está");
                throw  new StationNotFoundException();}

            return lista;

    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException {
        Bike b = null;
        Station sta = null;
        User user = this.users.get(userId);

        for(int i=0; i<this.numstations;){

            if(stationId.equals(this.stations[i].idStation)){

                sta = this.stations[i];

            }


        }
        if(user!=null){
            if (sta!=null){
                b = sta.consultaBikes().getFirst();
            }
            else {throw  new StationNotFoundException();}

        }else{throw  new UserNotFoundException();}
        return b;
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException{
        LinkedList<Bike> listaBikes = new LinkedList<Bike>();
        User u = users.get(userId);
        if ( u!=null){
            listaBikes = u.consultaBike();
        }
        else{throw new UserNotFoundException();}
        return listaBikes;
    }

    public int numStations(){
        return this.numstations;

    }

    public int numUsers() {

        return this.users.size();

    }
    public int numBikes(String idStation) throws StationNotFoundException {

        Station station = null;
        int numBikes;
        for(int i = 0; i<this.numStations(); i++) {
            if(idStation.equals(this.stations[i].idStation)){
                station = this.stations[i];
            }
        }
        if (station != null){
            numBikes = station.consultaBikes().size();
        }
        else{throw new StationNotFoundException();}
        return numBikes;
    }
    public void clear() {
        stations = null;
        numstations =0;
        users.clear();
        bikes.clear();
    }
}


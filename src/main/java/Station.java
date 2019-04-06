import java.util.LinkedList;

public class Station {

    String idStation;
    String description;
    int max;
    double lat;
    double lon;
    LinkedList<Bike> listaBike;

    public Station (String idStation, String description, int max, double lat, double lon){
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
    }


    public String getIdStation() {
        return idStation;
    }

    public String getDescription() {
        return description;
    }

    public int getMax() {
        return max;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void addBike(Bike b){
        this.listaBike.add(b);
    }
    public LinkedList<Bike> consultaBikes(){

        return this.listaBike;

    }

}

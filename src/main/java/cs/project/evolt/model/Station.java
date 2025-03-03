package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="Station")
public class Station {
    @Id
    @GeneratedValue
    @Column(name="station_id", unique=true)
    private UUID station_id;

    @Column(name="station_name")
    private String station_name;

    @Column(name="description")
    private String description;

    @Column(name="opentime")
    private String opentime;

    @Column(name="closetime")
    private String closetime;

    @Column(name="lat")
    private float lat;

    @Column(name="longitude")
    private float longitude;

    @Column(name="address")
    private String address;

    public Station(UUID station_id, String station_name, String description, String opentime, String closetime, float lat, float longitude, String address) {
        this.station_id = station_id;
        this.station_name = station_name;
        this.description = description;
        this.opentime = opentime;
        this.closetime = closetime;
        this.lat = lat;
        this.longitude = longitude;
        this.address = address;
    }

    public UUID getStation_id() {
        return station_id;
    }

    public void setStation_id(UUID station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Station{" +
                "station_id=" + station_id +
                ", station_name='" + station_name + '\'' +
                ", description='" + description + '\'' +
                ", opentime='" + opentime + '\'' +
                ", closetime='" + closetime + '\'' +
                ", lat=" + lat +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                '}';
    }
}

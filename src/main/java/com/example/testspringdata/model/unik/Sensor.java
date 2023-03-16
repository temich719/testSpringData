package com.example.testspringdata.model.unik;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int regionNumber;
    private String name;
    private double langitude;
    private double latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (id != sensor.id) return false;
        if (regionNumber != sensor.regionNumber) return false;
        if (Double.compare(sensor.langitude, langitude) != 0) return false;
        if (Double.compare(sensor.latitude, latitude) != 0) return false;
        return Objects.equals(name, sensor.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + regionNumber;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(langitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", regionNumber=" + regionNumber +
                ", name='" + name + '\'' +
                ", langitude=" + langitude +
                ", latitude=" + latitude +
                '}';
    }
}

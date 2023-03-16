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
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sensorNumber;
    private String registrationDate;
    private String registrationTime;
    private double temperature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (id != that.id) return false;
        if (sensorNumber != that.sensorNumber) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (!Objects.equals(registrationDate, that.registrationDate))
            return false;
        return Objects.equals(registrationTime, that.registrationTime);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + sensorNumber;
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", sensorNumber=" + sensorNumber +
                ", registrationDate='" + registrationDate + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}

package learn.spring.entity;

import java.util.Arrays;

public class Auditorium {
    private String name;
    private Integer numberOfSeats;
    private Integer[] vipSeats;

    public Auditorium(String name, Integer numberOfSeats, Integer[] vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer[] getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Integer[] vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public String toString() {
        return getName() + ". all seats: " + String.valueOf(getNumberOfSeats()) + " and VIP: " +
                Arrays.deepToString(getVipSeats());
    }
}

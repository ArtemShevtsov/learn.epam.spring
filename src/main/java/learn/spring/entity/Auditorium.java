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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;

        if (!name.equals(that.name)) return false;
        if (!numberOfSeats.equals(that.numberOfSeats)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(vipSeats, that.vipSeats);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + numberOfSeats.hashCode();
        result = 31 * result + Arrays.hashCode(vipSeats);
        return result;
    }
}

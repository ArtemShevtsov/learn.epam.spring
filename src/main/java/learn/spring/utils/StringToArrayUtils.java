package learn.spring.utils;


public class StringToArrayUtils {
    public static Integer[] converVipSeats(String seats){
        String[] vipSeats = seats.split(",");
        Integer[] vipSeatsInt = new Integer[vipSeats.length];
        for(int i = 0; i< vipSeats.length; i++){
            vipSeatsInt[i] = Integer.valueOf(vipSeats[i].trim());
        }
        return vipSeatsInt;
    }
}

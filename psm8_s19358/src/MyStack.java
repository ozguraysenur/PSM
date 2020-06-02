import java.util.ArrayList;
import java.util.List;

public class MyStack {
    public static final List<Coordinate> coordinates = new ArrayList<>();


    public static void push(Coordinate coordinate){
        coordinates.add(coordinate);
    }

    public static Coordinate pop(){
        if (!coordinates.isEmpty()){
            Coordinate last = coordinates.get(coordinates.size()-1);
            coordinates.remove(last);
            return last;
        }else{
           return null;
        }
    }
}
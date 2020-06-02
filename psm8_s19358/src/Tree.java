import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Tree {

    public static void main(String[] args) {

        MyStack stack =new MyStack();

        String initial= "X";
        String replace ="F+[[X]-X]-F[-FX]+X";
        String replaceff ="FF";
        for (int i = 0; i < 4; i++) {

            initial=initial.replace("F",replaceff);
            initial=initial.replace("X",replace);
        }
        double x=0;
        double y =0 ;
        double alfa =Math.toRadians(25);
        double l =1;

        char[] array =initial.toCharArray();

        System.out.println(initial);
        System.out.println(array.length);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data.txt"));


        for (int i = 0; i <array.length ; i++) {
            if(array[i]=='-'){
                alfa=alfa +Math.toRadians(25);
            }

            if(array[i]=='+'){
                alfa=alfa -Math.toRadians(25);
            }

            if(array[i]=='F'){
                x=x+ l*Math.cos(alfa);
            }
            if(array[i]=='F'){
                y=y+ l*Math.sin(alfa);
            }

            if(array[i]=='['){
                stack.push(new Coordinate(round(x),round(y),round(alfa)));

            }
            if(array[i]==']'){

                Coordinate co =stack.pop();
                x=co.x;
                y=co.y;
                alfa=co.alfa;
                bw.newLine();

            }
            if(array[i]!=']' && array[i]!='[' ) {
                bw.write(round(x) + "," + round(y) + "," + round(alfa));
                bw.newLine();
            }
        }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double round(double value) {

        long factor = (long) Math.pow(10, 3);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }



}

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 for radians\nPress 2 for degrees");
        int selection = scan.nextInt();
        System.out.println("Enter x");
        double input = scan.nextDouble();
        double radians = 0;
        if (selection == 2 ){
            //convert
            radians = input*(Math.PI/180);
        }else{
            radians = input;
        }
        //check if x is between 0 and 2pi, if not adjust
        while (radians >= 2){
            radians -= 2;
        }

        if (radians > (3*Math.PI)/2){
            radians = 2*Math.PI-radians;
        }
        if (radians > Math.PI){
            radians = radians-Math.PI;
        }
        if (radians > Math.PI/2){
            radians = Math.PI - radians;
        }

        //calculate using the previous term
        double curr = radians;
        int factor = 3;
        double previous = radians;
        System.out.println("Built-in | Mine | Diff");
        double sin = 0;
        for (int i = 0; i < 10; i++){
            System.out.print(Math.sin(radians)+" ");
            sin = (i%2 != 0)? sin-previous:sin+previous;
//            System.out.println(previous);
            previous = previous*((radians*radians)/(factor*factor-1));
            factor +=2;
            System.out.print(sin+ " ");
            System.out.println(Math.sin(radians)-sin);
        }


    }
}
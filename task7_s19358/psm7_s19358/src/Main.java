import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    int i=40;
    int j=40;
    List<String> equations=new ArrayList<>(40);
    List<String> b=new ArrayList<>();
    Map<String ,String> nodes=new HashMap<>();
    String [][] coeff =new String[1600][1600];


    List <String> edge100 = new ArrayList<>(40);
    List <String> edge200 = new ArrayList<>(40);
    List <String> edge50 = new ArrayList<>(40);
    List <String> edge150 = new ArrayList<>(40);
    public void run() {




        for (int k =1; k <= i; k++) {
            String str ="T("+k+",0)";
            edge150.add(str);
        }
        for (int k =1; k <= i; k++) {
            String str ="T(0,"+k+")";
            edge100.add(str);
        }
        for (int k =1; k <= i; k++) {
            String str ="T(41,"+k+")";
            edge50.add(str);
        }
        for (int k =1; k <= i; k++) {
            String str ="T("+k+",41)";
            edge200.add(str);
        }

        for (int k = 1; k <=i ; k++) {

            for (int l = 1; l <= j; l++) {
                nodes.put("T("+l+","+k+")","0");
                //System.out.println("T("+l+","+k+")");
            }
        }

        for (int k = 0; k < coeff.length; k++) {
            for (int l = 0; l < coeff[0].length; l++) {
                //System.out.println(coeff[k][l]);
                coeff[k][l]="0";
            }
        }

        //Collections.sort(coefficient);
        //System.out.println(coefficient);
        int row =0;
        for (int k = 1; k <= i; k++) {
            for (int l = 1; l <=j ; l++) {
                String equation= "T("+(l-1)+","+k+")+-4T("+l+","+k+")+T("+(l+1)+","+k+")+T("+l+","+(k-1)+")+T("+l+","+(k+1)+")=0";
                String after= calculation(equation);

                split(after,row);
                row++;
               // for (int m = 0; m < coeff[0].length; m++) {
                //    System.out.print(coeff[row][m]);
               // }
               // System.out.println();
               // TreeMap<String, String> sorted = new TreeMap<>(coefficient);
               // Set<Map.Entry<String, String>> mappings = sorted.entrySet();
                /*
                ArrayList<String> sortedKeys =new ArrayList<String>(coefficient.keySet());
                Collections.sort(sortedKeys);
                System.out.println("HashMap after sorting by keys in ascending order ");
                /*
                for(Map.Entry<String, String> mapping : mappings){
                    if(mapping.getValue().equals("1")||mapping.getValue().equals("(-4)")){
                    System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
                    }
                }
                */
                /*
                for (String x : sortedKeys)
                    System.out.println("Key = " + x +
                            ", Value = " + coefficient.get(x));

                */
                /*
                for (Map.Entry<String, String> entry : coefficient.entrySet()) {
                    if (entry.getValue().equals("1")||entry.getValue().equals("(-4)")) {
                        System.out.println(entry.getKey() + " " +entry.getValue());
                    }
                }*/
               // coefficient.replaceAll((key, oldValue) -> "0");
            }
        }
        writetoCSV();
/*
        for (int k = 0; k < b.size(); k++) {
             System.out.println(b.get(k));
        }
        */

/*        for (int k = 0; k < coeff.length; k++) {
            for (int l = 0; l < coeff[0].length; l++) {
                System.out.print(coeff[k][l]);
            }
            System.out.println();
        }
*/

    }

    public void split(String str,int index){
        String[] strArr= str.split("\\+");
        List<String> list =new ArrayList<>();
        list.addAll(Arrays.asList(strArr));
        String last =list.get(list.size()-1);
        String[] a =last.split("=");
        list.remove(list.size()-1);
        list.add(a[0]);
        takeCoefficients(list,index);
        b.add(a[1]);

    }


    public void writetoCSV(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\aysen\\Desktop\\dersler\\psm\\psm7_s19358\\src\\data.txt"));

            for (int i = 0; i < coeff.length; i++) {
                for (int j = 0; j < coeff[i].length; j++) {
                    if(j == coeff[i].length-1) {
                        bw.write(coeff[i][j]);
                    } else {
                        bw.write(coeff[i][j] + ",");
                    }
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {}


    }

    public String calculation(String str){


        for (int k = 0; k < edge100.size(); k++) {
            boolean bool =str.contains(edge100.get(k));
            if(bool){
                str=str.replace(edge100.get(k),"100");
            }
        }
        for (int k = 0; k < edge50.size(); k++) {
            boolean bool =str.contains(edge50.get(k));
            if(bool){
                str=str.replace(edge50.get(k),"50");
            }
        }for (int k = 0; k < edge150.size(); k++) {
            boolean bool =str.contains(edge150.get(k));
            if(bool){
                str= str.replace(edge150.get(k),"150");
            }
        }for (int k = 0; k < edge200.size(); k++) {
            boolean bool =str.contains(edge200.get(k));
            if(bool){
                str=str.replace(edge200.get(k),"200");
            }
        }


        String tmp =str;
        String[] strArr= tmp.split("\\+");
        List<String> list =new ArrayList<>();
        list.addAll(Arrays.asList(strArr));
        String last =list.get(list.size()-1);
        String[] a =last.split("="); 
        list.remove(list.size()-1);
        list.add(a[0]);
        list.add(a[1]);
        
        int sum= 0;
        for (int k = 0; k < list.size(); k++) {
            try {
                int numb= Integer.parseInt(list.get(k));
                sum+=numb;
                list.remove(list.get(k));
                k=k-1;
            }catch (Exception e){
                continue;
            }
        }
      //  list.remove(list.size()-1);
        list.add(""+sum);
        String equ="";
        for (int k = 0; k < list.size()-1; k++) {
           equ+=list.get(k)+"+";
        }
        equ = equ.substring(0, equ.length() - 1);
        equ+="="+"-"+sum;

        return equ;
    }

    public void takeCoefficients(List<String> list,int row){
        for (int k = 0; k < list.size(); k++) {
           // for ( String key : coefficient.keySet() ) {
             //  if(list.get(k).contains(key)){
                   String co = list.get(k).substring(0,list.get(k).indexOf('T'));
                   String ii =list.get(k).substring(list.get(k).indexOf('(')+1,list.get(k).indexOf(','));
                   String jj =list.get(k).substring(list.get(k).indexOf(',')+1,list.get(k).indexOf(')'));

                   int i1 =Integer.parseInt(ii);
                   int j1 =Integer.parseInt(jj);
                   if(co.equals("")){
                       co="1";
                   }
                    int index =(j1-1)*40+(i1-1);
                   //coefficient.put(key,co);
            /*

                        if(index==40){
                            index=39;
                        }

                        */
                   coeff[row][index]=co;

             //  }
           // }
        }
    }

    public static void main(String[] args) {

        Main main=new Main();
        main.run();
    }
}

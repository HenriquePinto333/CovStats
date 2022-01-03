import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CovStats {
    public static void main(String[] args) throws FileNotFoundException {

        String[][] matrix = new String[10000][6];
        //Data Diario_nao_infetado Acumulado_infetado Acumulado_hospitalizado Acumulado_internadoUCI Acumulado_mortes
        String[] date = new String[10000];
        int[] NI = new int[10000];
        int[] I = new int[10000];
        int[] H = new int[10000];
        int[] UCI = new int[10000];
        int[] M = new int[10000];
        //call method readmatrix and declare the number of lines
        int linenumber = readmatrix( matrix, date, NI, I, H, UCI, M);
    }
    public static int readmatrix(String[][] matrix,String[] date, int[] NI,int[] I, int[] H, int[] UCI, int[] M) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("info"));
        String desc = sc.nextLine();
        String[] fsline = desc.split(",");
        int linenumber = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] split = line.split(",");
             date[linenumber] = split[0];
             NI[linenumber] = Integer.parseInt(split[1]);
             I[linenumber] = Integer.parseInt(split[2]);
             H[linenumber] = Integer.parseInt(split[3]);
             UCI[linenumber] = Integer.parseInt(split[4]);
             M[linenumber] = Integer.parseInt(split[5]);
             //changes line to next line
            if(sc.hasNextLine()) {
                linenumber++;
            }
        }

        for( int x = 0 ; x <= linenumber ; x++ ){
            System.out.printf("%s %d %d %d %d %d\n", date[x],NI[x],I[x],H[x],UCI[x],M[x]);
        }
        System.out.printf("%d\n", linenumber);
        //return the number of lines to the main method, it might be useful
        return linenumber;
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CovStats {
    public static void main(String[] args) throws FileNotFoundException {

        //Data Diario_nao_infetado Acumulado_infetado Acumulado_hospitalizado Acumulado_internadoUCI Acumulado_mortes
        String[] date = new String[10000];
        int[] NI = new int[10000];
        int[] I = new int[10000];
        int[] H = new int[10000];
        int[] UCI = new int[10000];
        int[] M = new int[10000];
        //call method readmatrix and declare the number of lines
        int linenumber = readmatrix(date, NI, I, H, UCI, M);
        comparacoes(date, NI, I, H, UCI, M, linenumber);

    }
    public static int readmatrix(String[] date, int[] NI,int[] I, int[] H, int[] UCI, int[] M) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\Henrique\\Desktop\\exemploRegistoNumerosCovid19 (1).csv"));
        String desc = sc.nextLine();
        String[] fsline = desc.split(",");
        int linenumber = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] split = line.split(",");
             date[linenumber] = split[0]; //data
             NI[linenumber] = Integer.parseInt(split[1]); //Não infetados
             I[linenumber] = Integer.parseInt(split[2]); //Infetados
             H[linenumber] = Integer.parseInt(split[3]); //Hospitalizado
             UCI[linenumber] = Integer.parseInt(split[4]); //Internados UCI
             M[linenumber] = Integer.parseInt(split[5]); //Mortos

             //changes line to next line
            if(sc.hasNextLine()) {
                linenumber++;
            }
        }
        for( int x = 0 ; x <= linenumber ; x++ ){
            System.out.printf("%s %d %d %d %d %d\n", date[x],NI[x],I[x],H[x],UCI[x],M[x]);
        }

        //return the number of lines to the main method, it might be useful
        return linenumber;
    }
    public static void comparacoes (String[] date, int[] NI,int[] I, int[] H, int[] UCI, int[] M, int linenumber) {
        Scanner ler = new Scanner(System.in);
        String data1,data2;
        int tipo,linha1=0,linha2=0,linha1aux,linha2aux,dI,dH,dUCI,dM;
        System.out.println("Escolha o tipo de intervalos quer comparar");
        System.out.println("1-Dias/2-Semanas/3-Meses/4-Trimestres/5-Anos");
        tipo=ler.nextInt();
        ler.nextLine();
        System.out.println("Escolha a primeira data(copiar a data da matriz imprimida):");
        data1= ler.nextLine(); //ler data1
        System.out.println("Escolha a segunda data(copiar a data da matriz imprimida):");
        data2=ler.nextLine(); //ler data2

        int x=0;
        while(x < linenumber && !Objects.equals(data1, date[x])){
            if(!Objects.equals(data1, date[x])){
                linha1++;
            }
            x++;
        }
        int y=0;
        while(y < linenumber && !Objects.equals(data2, date[y])){
            if(!Objects.equals(data2, date[y])){
                linha2++;
            }
            y++;
        }

        switch (tipo){
            case 1 :
                dI=I[linha2]-I[linha1];
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" infeções");}
                else { System.out.println("Houve um aumento de "+dI+" infeções"); }
                dH=H[linha2]-H[linha1];
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" hospitalizações");}
                else { System.out.println("Houve um aumento de "+dH+" hospitalizações"); }
                dUCI=UCI[linha2]-UCI[linha1];
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" entradas nos cuidados intensivos");}
                else { System.out.println("Houve um aumento de "+dUCI+" entradas nos cuidados intensivos"); }
                dM=M[linha2]-M[linha1];
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" mortos");}
                else { System.out.println("Houve um aumento de "+dM+" mortos"); }
                break; //dias

            case 2 :
                linha1aux=linha1+7;
                linha2aux=linha2+7;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" infeções");}
                else System.out.println("Houve um aumento de "+dI+" infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" mortos");}
                else System.out.println("Houve um aumento de "+dM+" mortos");
                break; //semanas
            case 3 :
                linha1aux=linha1+30;
                linha2aux=linha2+30;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" infeções");}
                else System.out.println("Houve um aumento de "+dI+" infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" mortos");}
                else System.out.println("Houve um aumento de "+dM+" mortos");
                break; //meses
            case 4 :
                linha1aux=linha1+90;
                linha2aux=linha2+90;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" infeções");}
                else System.out.println("Houve um aumento de "+dI+" infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" mortos");}
                else System.out.println("Houve um aumento de "+dM+" mortos");
                break; //trimestres
            case 5 :
                linha1aux=linha1+365;
                linha2aux=linha2+365;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" infeções");}
                else System.out.println("Houve um aumento de "+dI+" infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" mortos");}
                else System.out.println("Houve um aumento de "+dM+" mortos");
                break; //anos
        }

    }

}

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
        String di,df;
        int r,linha1=0,linha2=0,linha1aux,linha2aux,dI,dH,dUCI,dM;
        System.out.println("Escolha o tipo de intervalos quer comparar");
        System.out.println("0-Dias/1-Semanas/2-Meses/3-Trimestres");
        r=ler.nextInt();
        ler.nextLine();
        System.out.println("Escolha a primeira data(copiar a data da matriz imprimida):");
        di= ler.nextLine(); //ler data1
        System.out.println("Escolha a segunda data(copiar a data da matriz imprimida):");
        df=ler.nextLine(); //ler data2

        int x=0;
        while(x < linenumber && !Objects.equals(di, date[x])){
            if(!Objects.equals(di, date[x])){
                linha1++;
            }
            x++;
        }
        int y=0;
        while(y < linenumber && !Objects.equals(df, date[y])){
            if(!Objects.equals(df, date[y])){
                linha2++;
            }
            y++;
        }

        switch (r){
            case 0 :
                dI=I[linha2]-I[linha1];
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" novas infeções");}
                else { System.out.println("Houve um aumento de "+dI+" novas infeções"); }
                dH=H[linha2]-H[linha1];
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" novas hospitalizações");}
                else { System.out.println("Houve um aumento de "+dH+" novas hospitalizações"); }
                dUCI=UCI[linha2]-UCI[linha1];
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" novas entradas nos cuidados intensivos");}
                else { System.out.println("Houve um aumento de "+dUCI+" novas entradas nos cuidados intensivos"); }
                dM=M[linha2]-M[linha1];
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" novas mortes");}
                else { System.out.println("Houve um aumento de "+dM+" novas mortes"); }

                int i = linha2 - linha1;
                int mediaI = dI / i;
                System.out.println("A média de infeções neste período foi de " + mediaI + " infeções por dia.");
                int mediaH = dH / i;
                System.out.println("A média de novas hospitalizações neste período foi de " + mediaH + " por dia.");
                int mediaUCI = dUCI / i;
                System.out.println("A média de novas entradas nos cuidados intensivos neste período foi de " + mediaUCI + " por dia.");
                int mediaM = dM / i;
                System.out.println("A média de mortes neste período foi de " + mediaM + " por dia.");

                // desvio padrão
                double newpe= i-1;
                System.out.println("");
                System.out.println ( "O desvio padrão é o seguinte: ");
                double  midvalue=  ((linha2- mediaI)*(linha2 - mediaI))/newpe;
                double  desviopad = Math.pow ( midvalue, 0.5);
                System.out.println("Novos Infetados: ");
                System.out.printf("%.2f", desviopad);
                double  midvalue2=  ((linha2- mediaH)*(linha2 - mediaH))/newpe;
                double  desviopad2 = Math.pow ( midvalue2, 0.5);
                System.out.println("Novos Hospitalizados: ");
                System.out.printf("%.2f", desviopad2);
                double  midvalue3=  ((linha2- mediaUCI)*(linha2 - mediaUCI))/newpe;
                double  desviopad3 = Math.pow ( midvalue3, 0.5);
                System.out.println("Novos em cuidados UCI: ");
                System.out.printf("%.2f", desviopad3);
                double  midvalue4=  ((linha2- mediaM)*(linha2 - mediaM))/newpe;
                double  desviopad4 = Math.pow ( midvalue4, 0.5);
                System.out.println("Novos Mortos: ");
                System.out.printf("%.2f", desviopad4);

                break; //dias



            case 1 :
                linha1aux=linha1+7;
                linha2aux=linha2+7;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" novas infeções");}
                else System.out.println("Houve um aumento de "+dI+" novas infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" novas hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" novas hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" novas entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" novas entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" novas mortes");}
                else System.out.println("Houve um aumento de "+dM+" novas mortes");

                int j = linha2 - linha1;
                int mediaI2 = dI / j;
                System.out.println("A média de infeções neste período foi de " + mediaI2 + " infeções por dia.");
                int mediaH2 = dH / j;
                System.out.println("A média de novas hospitalizações neste período foi de " + mediaH2+ " por dia.");
                int mediaUCI2 = dUCI / j;
                System.out.println("A média de novas entradas nos cuidados intensivos neste período foi de " + mediaUCI2 + " por dia.");
                int mediaM2 = dM / j;
                System.out.println("A média de mortes neste período foi de " + mediaM2 + " por dia.");

                // desvio padrão
                double newpe2= j-1;
                System.out.println("");
                System.out.println ( "O desvio padrão é o seguinte: ");
                double  midvalue1=  ((linha2- mediaI2)*(linha2 - mediaI2))/newpe2;
                double  desviopad1 = Math.pow ( midvalue1, 0.5);
                System.out.println("Novos Infetados: ");
                System.out.printf("%.2f", desviopad1);
                double  midvalue22=  ((linha2- mediaH2)*(linha2 - mediaH2))/newpe2;
                double  desviopad22 = Math.pow ( midvalue22, 0.5);
                System.out.println("Novos Hospitalizados: ");
                System.out.printf("%.2f", desviopad22);
                double  midvalue32=  ((linha2- mediaUCI2)*(linha2 - mediaUCI2))/newpe2;
                double  desviopad32 = Math.pow ( midvalue32, 0.5);
                System.out.println("Novos em cuidados UCI: ");
                System.out.printf("%.2f", desviopad32);
                double  midvalue42=  ((linha2- mediaM2)*(linha2 - mediaM2))/newpe2;
                double  desviopad42 = Math.pow ( midvalue42, 0.5);
                System.out.println("Novos Mortos: ");
                System.out.printf("%.2f", desviopad42);

                break; //semanas
            case 2 :
                linha1aux=linha1+30;
                linha2aux=linha2+30;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" novas infeções");}
                else System.out.println("Houve um aumento de "+dI+" novas infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" novas hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" novas hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" novas entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" novas entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" novas mortes");}
                else System.out.println("Houve um aumento de "+dM+" novas mortes");

                int k = linha2 - linha1;
                int mediaI3 = dI / k;
                System.out.println("A média de infeções neste período foi de " + mediaI3 + " infeções por dia.");
                int mediaH3 = dH / k;
                System.out.println("A média de novas hospitalizações neste período foi de " + mediaH3 + " por dia.");
                int mediaUCI3 = dUCI / k;
                System.out.println("A média de novas entradas nos cuidados intensivos neste período foi de " + mediaUCI3 + " por dia.");
                int mediaM3 = dM / k;
                System.out.println("A média de mortes neste período foi de " + mediaM3 + " por dia.");

                // desvio padrão
                double newpe3= k-1;
                System.out.println("");
                System.out.println ( "O desvio padrão é o seguinte: ");
                double  midvalue333=  ((linha2- mediaI3)*(linha2 - mediaI3))/newpe3;
                double  desviopad333 = Math.pow ( midvalue333, 0.5);
                System.out.println("Novos Infetados: ");
                System.out.printf("%.2f", desviopad333);
                double  midvalue23=  ((linha2- mediaH3)*(linha2 - mediaH3))/newpe3;
                double  desviopad23 = Math.pow ( midvalue23, 0.5);
                System.out.println("Novos Hospitalizados: ");
                System.out.printf("%.2f", desviopad23);
                double  midvalue33=  ((linha2- mediaUCI3)*(linha2 - mediaUCI3))/newpe3;
                double  desviopad33 = Math.pow ( midvalue33, 0.5);
                System.out.println("Novos em cuidados UCI: ");
                System.out.printf("%.2f", desviopad33);
                double  midvalue43=  ((linha2- mediaM3)*(linha2 - mediaM3))/newpe3;
                double  desviopad43 = Math.pow ( midvalue43, 0.5);
                System.out.println("Novos Mortos: ");
                System.out.printf("%.2f", desviopad43);

                break; //meses
            case 3 :
                linha1aux=linha1+90;
                linha2aux=linha2+90;
                dI=(I[linha2aux]-I[linha2])-(I[linha1aux]-I[linha1]);
                if (dI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dI)+" novas infeções");}
                else System.out.println("Houve um aumento de "+dI+" novas infeções");
                dH=(H[linha2aux]-H[linha2])-(H[linha1aux]-H[linha1]);
                if (dH<0){ System.out.println("Houve uma diminuição de "+Math.abs(dH)+" novas hospitalizações");}
                else System.out.println("Houve um aumento de "+dH+" novas hospitalizações");
                dUCI=(UCI[linha2aux]-UCI[linha2])-(UCI[linha1aux]-UCI[linha1]);
                if (dUCI<0){ System.out.println("Houve uma diminuição de "+Math.abs(dUCI)+" novas entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de "+dUCI+" novas entradas nos cuidados intensivos");
                dM=(M[linha2aux]-M[linha2])-(M[linha1aux]-M[linha1]);
                if (dM<0){ System.out.println("Houve uma diminuição de "+Math.abs(dM)+" novas mortes");}
                else System.out.println("Houve um aumento de "+dM+" novas mortes");

                int l = linha2 - linha1;
                int mediaI4 = dI / l;
                System.out.println("A média de infeções neste período foi de " + mediaI4 + " infeções por dia.");
                int mediaH4 = dH / l;
                System.out.println("A média de novas hospitalizações neste período foi de " + mediaH4 + " por dia.");
                int mediaUCI4 = dUCI / l;
                System.out.println("A média de novas entradas nos cuidados intensivos neste período foi de " + mediaUCI4 + " por dia.");
                int mediaM4 = dM / l;
                System.out.println("A média de mortes neste período foi de " + mediaM4+ " por dia.");

                // desvio padrão
                double newpe4= l-1;
                System.out.println("");
                System.out.println ( "O desvio padrão é o seguinte: ");
                double  midvalue44=  ((linha2- mediaI4)*(linha2 - mediaI4))/newpe4;
                double  desviopad44 = Math.pow ( midvalue44, 0.5);
                System.out.println("Novos Infetados: ");
                System.out.printf("%.2f", desviopad44);
                double  midvalue24=  ((linha2- mediaH4)*(linha2 - mediaH4))/newpe4;
                double  desviopad24 = Math.pow ( midvalue24, 0.5);
                System.out.println("Novos Hospitalizados: ");
                System.out.printf("%.2f", desviopad24);
                double  midvalue34=  ((linha2- mediaUCI4)*(linha2 - mediaUCI4))/newpe4;
                double  desviopad34 = Math.pow ( midvalue34, 0.5);
                System.out.println("Novos em cuidados UCI: ");
                System.out.printf("%.2f", desviopad34);
                double  midvalue444=  ((linha2- mediaM4)*(linha2 - mediaM4))/newpe4;
                double  desviopad444 = Math.pow ( midvalue444, 0.5);
                System.out.println("Novos Mortos: ");
                System.out.printf("%.2f", desviopad444);

                break; //trimestres
        }



    }

}
//double sum = 0; (exemplo)
/* for ( i=linha1 ; i <= linha 2 ; i++ ){
  sum = sum+(I[i]-média)*(I[i]-média);
}
desviopad = Math.pow((sum/newpe5), 0.5)
 */
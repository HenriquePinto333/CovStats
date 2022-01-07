import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class CovStats {
    public static void main(String[] args) throws FileNotFoundException, ParseException {

        //Data Diario_nao_infetado Acumulado_infetado Acumulado_hospitalizado Acumulado_internadoUCI Acumulado_mortes
        String[] date = new String[10000];
        int[] NI = new int[10000];
        int[] I = new int[10000];
        int[] H = new int[10000];
        int[] UCI = new int[10000];
        int[] M = new int[10000];
        //call method readmatrix and declare the number of lines
        int linenumber = readmatrix(date, NI, I, H, UCI, M);
       //call method necessary to compare dates
        comparacoes(date, NI, I, H, UCI, M, linenumber);

    }
    public static int readmatrix(String[] date, int[] NI,int[] I, int[] H, int[] UCI, int[] M) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(new File("C:\\Users\\Henrique\\Desktop\\exemploRegistoNumerosCovid19 (1).csv"));
        String desc = sc.nextLine();
        String[] fsline = desc.split(",");
        //convert dates into required format
        String oldformat = "yyyy-mm-dd";
        String newformat = "dd-mm-yyyy";
        int linenumber = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] split = line.split(",");
             date[linenumber] = split[0]; //data
            SimpleDateFormat sdf = new SimpleDateFormat(oldformat);
            Date d = sdf.parse(date[linenumber]);
            sdf.applyPattern(newformat);
            date[linenumber] = sdf.format(d);
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
        double sum = 0;
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;

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
                System.out.printf ( "\nO desvio padrão é o seguinte: \n");
                //desvio infetados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum = sum+(I[i]-mediaI)*(I[i]-mediaI);
                }
                double midvalue = sum/newpe;
                double  desviopad = Math.pow ( midvalue, 0.5);
                System.out.printf("Desvio dos Infetados: %.2f\n", desviopad);
                //desvio hospitalizados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum1 = sum1+(H[i]-mediaH)*(H[i]-mediaH);
                }
                double midvalue2 = sum1/newpe;
                double  desviopad2 = Math.pow ( midvalue2, 0.5);
                System.out.printf("Desvio dos Hospitalizados: %.2f\n", desviopad2);
                //desvio cuidados UCI
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum2 = sum2+(UCI[i]-mediaUCI)*(UCI[i]-mediaUCI);
                }
                double  midvalue3=  sum2/newpe;
                double  desviopad3 = Math.pow ( midvalue3, 0.5);
                System.out.printf("Desvio dos cuidados UCI: %.2f\n", desviopad3);
                //desvio Mortos
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum3 = sum3+(M[i]-mediaM)*(M[i]-mediaM);
                }
                double  midvalue4 =  sum3/newpe;
                double  desviopad4 = Math.pow ( midvalue4, 0.5);
                System.out.printf("Desvio dos Mortos: %.2f\n", desviopad4);

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
                System.out.printf( "\nO desvio padrão é o seguinte: \n");
                //desvio infetados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum = sum+(I[i]-mediaI2)*(I[i]-mediaI2);
                }
                double midvalue12 = sum/newpe2;
                double  desviopad12 = Math.pow ( midvalue12, 0.5);
                System.out.printf("Desvio dos Infetados: %.2f\n", desviopad12);
                //desvio hospitalizados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum1 = sum1+(H[i]-mediaH2)*(H[i]-mediaH2);
                }
                double midvalue22 = sum1/newpe2;
                double  desviopad22 = Math.pow ( midvalue22, 0.5);
                System.out.printf("Desvio dos Hospitalizados: %.2f\n", desviopad22);
                //desvio cuidados UCI
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum2 = sum2+(UCI[i]-mediaUCI2)*(UCI[i]-mediaUCI2);
                }
                double  midvalue32=  sum2/newpe2;
                double  desviopad32 = Math.pow ( midvalue32, 0.5);
                System.out.printf("Desvio dos cuidados UCI: %.2f\n", desviopad32);
                //desvio Mortos
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum3 = sum3+(M[i]-mediaM2)*(M[i]-mediaM2);
                }
                double  midvalue42 =  sum3/newpe2;
                double  desviopad42 = Math.pow ( midvalue42, 0.5);
                System.out.printf("Desvio dos Mortos: %.2f\n", desviopad42);

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
                System.out.printf ( "\nO desvio padrão é o seguinte: \n");
                //desvio infetados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum = sum+(I[i]-mediaI3)*(I[i]-mediaI3);
                }
                double midvalue13 = sum/newpe3;
                double  desviopad13 = Math.pow ( midvalue13, 0.5);
                System.out.printf("Desvio dos Infetados: %.2f\n", desviopad13);
                //desvio hospitalizados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum1 = sum1+(H[i]-mediaH3)*(H[i]-mediaH3);
                }
                double midvalue23 = sum1/newpe3;
                double  desviopad23 = Math.pow ( midvalue23, 0.5);
                System.out.printf("Desvio dos Hospitalizados: %.2f\n", desviopad23);
                //desvio cuidados UCI
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum2 = sum2+(UCI[i]-mediaUCI3)*(UCI[i]-mediaUCI3);
                }
                double  midvalue33=  sum2/newpe3;
                double  desviopad33 = Math.pow ( midvalue33, 0.5);
                System.out.printf("Desvio dos cuidados UCI: %.2f\n", desviopad33);
                //desvio Mortos
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum3 = sum3+(M[i]-mediaM3)*(M[i]-mediaM3);
                }
                double  midvalue43 =  sum3/newpe3;
                double  desviopad43 = Math.pow ( midvalue43, 0.5);
                System.out.printf("Desvio dos Mortos: %.2f\n", desviopad43);

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
                System.out.printf ( "\nO desvio padrão é o seguinte: \n");
                //desvio infetados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                sum = sum+(I[i]-mediaI4)*(I[i]-mediaI4);
                }
                double midvalue14 = sum/newpe4;
                double  desviopad14 = Math.pow ( midvalue14, 0.5);
                System.out.printf("Desvio dos Infetados: %.2f\n", desviopad14);
                //desvio hospitalizados
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum1 = sum1+(H[i]-mediaH4)*(H[i]-mediaH4);
                }
                double midvalue24 = sum1/newpe4;
                double  desviopad24 = Math.pow ( midvalue24, 0.5);
                System.out.printf("Desvio dos Hospitalizados: %.2f\n", desviopad24);
                //desvio cuidados UCI
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum2 = sum2+(UCI[i]-mediaUCI4)*(UCI[i]-mediaUCI4);
                }
                double  midvalue34=  sum2/newpe4;
                double  desviopad34 = Math.pow ( midvalue34, 0.5);
                System.out.printf("Desvio dos cuidados UCI: %.2f\n", desviopad34);
                //desvio Mortos
                for ( i=linha1 ; i <= linha2 ; i++ ){
                    sum3 = sum3+(M[i]-mediaM4)*(M[i]-mediaM4);
                }
                double  midvalue44=  sum3/newpe4;
                double  desviopad44 = Math.pow ( midvalue44, 0.5);
                System.out.printf("Desvio dos Mortos: %.2f\n", desviopad44);

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
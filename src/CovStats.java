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
        comparaçoes(date, NI, I, H, UCI, M);

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
    public static void comparaçoes (String[] date, int[] NI,int[] I, int[] H, int[] UCI, int[] M) {
        Scanner ler = new Scanner(System.in);
        String data1,data2,dataaux1,dataaux2;
        int tipo, x=0,linha1=0,linha2=0,linha1aux=0,linha2aux=0,dI,dH,dUCI,dM;
        System.out.println("Escolha o tipo de intervalos quer comparar");
        System.out.println("1-Dias/2-Semanas/3-Meses/4-Trimestres/5-Anos");
        tipo=ler.nextInt();
        System.out.println("Escolha a primeira data");
        data1= ler.nextLine(); //ler data1
        System.out.println("Escolha a segunda data");
        data2=ler.nextLine(); //ler data2
        while(x< date.length && data1!=date[x]){ //linha da data1
            date[x]=date[x+1];
            linha1++;
        }
        x=0;
        while(x< date.length && data2!=date[x]){ //linha da data2
            date[x]=date[x+1];
            linha2++;
        }
        switch (tipo){
            case 1 :
                dI=I[linha1]-I[linha2];
                if (dI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dI)+"infeções");}
                else System.out.println("Houve um aumento de"+dI+"infeções");
                dH=H[linha1]-H[linha2];
                if (dH<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dH)+"hospitalizações");}
                else System.out.println("Houve um aumento de"+dH+"hospitalizações");
                dUCI=UCI[linha1]-UCI[linha2];
                if (dUCI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dUCI)+"entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de"+dUCI+"entradas nos cuidados intensivos");
                dM=M[linha1]-M[linha2];
                if (dM<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dM)+"mortos");}
                else System.out.println("Houve um aumento de"+dM+"mortos");
                ; break; //dias
            case 2 :
                linha1aux=linha1+8;
                linha2aux=linha2+8;
                dI=(I[linha1aux]-I[linha1])-(I[linha2aux]-I[linha2]);
                if (dI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dI)+"infeções");}
                else System.out.println("Houve um aumento de"+dI+"infeções");
                dH=(H[linha1aux]-H[linha1])-(H[linha2aux]-H[linha2]);
                if (dH<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dH)+"hospitalizações");}
                else System.out.println("Houve um aumento de"+dH+"hospitalizações");
                dUCI=(UCI[linha1aux]-UCI[linha1])-(UCI[linha2aux]-UCI[linha2]);
                if (dUCI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dUCI)+"entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de"+dUCI+"entradas nos cuidados intensivos");
                dM=(M[linha1aux]-M[linha1])-(M[linha2aux]-M[linha2]);
                if (dM<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dM)+"mortos");}
                else System.out.println("Houve um aumento de"+dM+"mortos");
                ; break; //semanas
            case 3 :
                linha1aux=linha1+30;
                linha2aux=linha2+30;
                dI=(I[linha1aux]-I[linha1])-(I[linha2aux]-I[linha2]);
                if (dI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dI)+"infeções");}
                else System.out.println("Houve um aumento de"+dI+"infeções");
                dH=(H[linha1aux]-H[linha1])-(H[linha2aux]-H[linha2]);
                if (dH<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dH)+"hospitalizações");}
                else System.out.println("Houve um aumento de"+dH+"hospitalizações");
                dUCI=(UCI[linha1aux]-UCI[linha1])-(UCI[linha2aux]-UCI[linha2]);
                if (dUCI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dUCI)+"entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de"+dUCI+"entradas nos cuidados intensivos");
                dM=(M[linha1aux]-M[linha1])-(M[linha2aux]-M[linha2]);
                if (dM<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dM)+"mortos");}
                else System.out.println("Houve um aumento de"+dM+"mortos");
                ; break; //meses
            case 4 :
                linha1aux=linha1+90;
                linha2aux=linha2+90;
                dI=(I[linha1aux]-I[linha1])-(I[linha2aux]-I[linha2]);
                if (dI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dI)+"infeções");}
                else System.out.println("Houve um aumento de"+dI+"infeções");
                dH=(H[linha1aux]-H[linha1])-(H[linha2aux]-H[linha2]);
                if (dH<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dH)+"hospitalizações");}
                else System.out.println("Houve um aumento de"+dH+"hospitalizações");
                dUCI=(UCI[linha1aux]-UCI[linha1])-(UCI[linha2aux]-UCI[linha2]);
                if (dUCI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dUCI)+"entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de"+dUCI+"entradas nos cuidados intensivos");
                dM=(M[linha1aux]-M[linha1])-(M[linha2aux]-M[linha2]);
                if (dM<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dM)+"mortos");}
                else System.out.println("Houve um aumento de"+dM+"mortos");
                ; break; //trimestres
            case 5 :
                linha1aux=linha1+365;
                linha2aux=linha2+365;
                dI=(I[linha1aux]-I[linha1])-(I[linha2aux]-I[linha2]);
                if (dI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dI)+"infeções");}
                else System.out.println("Houve um aumento de"+dI+"infeções");
                dH=(H[linha1aux]-H[linha1])-(H[linha2aux]-H[linha2]);
                if (dH<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dH)+"hospitalizações");}
                else System.out.println("Houve um aumento de"+dH+"hospitalizações");
                dUCI=(UCI[linha1aux]-UCI[linha1])-(UCI[linha2aux]-UCI[linha2]);
                if (dUCI<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dUCI)+"entradas nos cuidados intensivos");}
                else System.out.println("Houve um aumento de"+dUCI+"entradas nos cuidados intensivos");
                dM=(M[linha1aux]-M[linha1])-(M[linha2aux]-M[linha2]);
                if (dM<0){ System.out.println("Houve uma dimunuição de"+Math.abs(dM)+"mortos");}
                else System.out.println("Houve um aumento de"+dM+"mortos");
                ; break; //anos
        }

    }

}

package guioptimiser;

import Calculator.AppColor;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
class KidsGA{
    public Color mainFrameColor;
    public Color jButton2;
    public Color jTextField1;
    public Color jLabel1;
    public Color jPanel1;
    public Color jPanel5;
    public Color jPanel2;
    public Color jButton1;
    public Color jButton3;
    public Color jButton4;
    public Color jButton5;
    public Color jButton6;
    public Color jButton7;
    public Color jButton8;
    public Color jButton9;
    public Color jButton10;
    public Color jPanel3;
    public Color jButton11;
    public Color jButton15;
    public Color jButton16;
    public Color jButton17;
    public Color jButton18;
    public Color jPanel4;
    public Color jButton12;
    public Color jButton13;
    public Color jButton14;
    public Color jTextField1TextColor;

    public KidsGA(){

    }
}
public class GA {
    static int kidsNum = 1;
    static int mutationTimes = 4;
    static LinkedList<KidsGA> populationGA = new LinkedList<>();
    int[][] colorGA = new int[kidsNum][3];
    int[] bestGA = new int[3];
    int[] parent = new int[3];
    public void GA(){}

    public void makeNewSolution() throws IOException {
        KidsGA kidGA = new KidsGA();
        newAppColor ap = new newAppColor();
        kidGA.mainFrameColor = ap.mainFrameColor;
        kidGA.jButton1 = ap.jButton1;
        kidGA.jButton2 = ap.jButton2;
        kidGA.jButton3 = ap.jButton3;
        kidGA.jButton4 = ap.jButton4;
        kidGA.jButton5 = ap.jButton5;
        kidGA.jButton6 = ap.jButton6;
        kidGA.jButton7 = ap.jButton7;
        kidGA.jButton8 = ap.jButton8;
        kidGA.jButton9 = ap.jButton9;
        kidGA.jButton10 = ap.jButton10;
        kidGA.jButton11 = ap.jButton11;
        kidGA.jButton12 = ap.jButton12;
        kidGA.jButton13 = ap.jButton13;
        kidGA.jButton14 = ap.jButton14;
        kidGA.jButton15 = ap.jButton15;
        kidGA.jButton16 = ap.jButton16;
        kidGA.jButton17 = ap.jButton17;
        kidGA.jButton18 = ap.jButton18;
        kidGA.jTextField1 = ap.jTextField1;
        kidGA.jTextField1TextColor = ap.jTextField1TextColor;
        kidGA.jLabel1 = ap.jLabel1;
        kidGA.jPanel1 = ap.jPanel1;
        kidGA.jPanel2 = ap.jPanel2;
        kidGA.jPanel3 = ap.jPanel3;
        kidGA.jPanel4 = ap.jPanel4;
        kidGA.jPanel5 = ap.jPanel5;

  //      printColor(kidGA);
        populationGA.add(kidGA);
    }
    public static void printColor(KidsGA kidGA){
        System.out.print(kidGA.mainFrameColor+"");
        System.out.print(kidGA.jButton1+"");
        System.out.print(kidGA.jButton2+"");
        System.out.print(kidGA.jButton3+"");
        System.out.print(kidGA.jButton4+"");
        System.out.print(kidGA.jButton5+"");
        System.out.print(kidGA.jButton6+"");
        System.out.print(kidGA.jButton7+"");
        System.out.print(kidGA.jButton8+"");
        System.out.print(kidGA.jButton9+"");
        System.out.print(kidGA.jButton10+"");
        System.out.print(kidGA.jButton11+"");
        System.out.print(kidGA.jButton12+"");
        System.out.print(kidGA.jButton13+"");
        System.out.print(kidGA.jButton14+"");
        System.out.print(kidGA.jButton15+"");
        System.out.print(kidGA.jButton16+"");
        System.out.print(kidGA.jButton17+"");
        System.out.print(kidGA.jButton18+"");
        System.out.print(kidGA.jTextField1+"");
        System.out.print(kidGA.jTextField1TextColor+"");
        System.out.print(kidGA.jLabel1+"");
        System.out.print(kidGA.jPanel1+"");
        System.out.print(kidGA.jPanel2+"");
        System.out.print(kidGA.jPanel3+"");
        System.out.print(kidGA.jPanel4+"");
        System.out.println(kidGA.jPanel5+"");

    }
    public static void readFromExcel(String kid) throws IOException {


    }
    public static KidsGA mutation(KidsGA kid1){
        KidsGA kid = kid1;
        for(int i = 0; i < mutationTimes; i++){
            int j = (int)(Math.random() * 27);

            switch (j){
                case 0: kid.mainFrameColor = mutationOperation(kid.mainFrameColor);break;
                case 1: kid.jButton1 = mutationOperation(kid.jButton1);break;
                case 2: kid.jButton2 = mutationOperation(kid.jButton2);break;
                case 3: kid.jButton3 = mutationOperation(kid.jButton3);break;
                case 4: kid.jButton4 = mutationOperation(kid.jButton4);break;
                case 5: kid.jButton5 = mutationOperation(kid.jButton5);break;
                case 6: kid.jButton6 = mutationOperation(kid.jButton6);break;
                case 7: kid.jButton7 = mutationOperation(kid.jButton7);break;
                case 8: kid.jButton8 = mutationOperation(kid.jButton8);break;
                case 9: kid.jButton9 = mutationOperation(kid.jButton9);break;
                case 10: kid.jButton10 = mutationOperation(kid.jButton10);break;
                case 11: kid.jButton11 = mutationOperation(kid.jButton11);break;
                case 12: kid.jButton12 = mutationOperation(kid.jButton12);break;
                case 13: kid.jButton13 = mutationOperation(kid.jButton13);break;
                case 14: kid.jButton14 = mutationOperation(kid.jButton14);break;
                case 15: kid.jButton15 = mutationOperation(kid.jButton15);break;
                case 16: kid.jButton16 = mutationOperation(kid.jButton16);break;
                case 17: kid.jButton17 = mutationOperation(kid.jButton17);break;
                case 18: kid.jButton18 = mutationOperation(kid.jButton18);break;
                case 19: kid.jTextField1 = mutationOperation(kid.jTextField1);break;
                case 20: kid.jTextField1TextColor = mutationOperation(kid.jTextField1TextColor);break;
                case 21: kid.jLabel1 = mutationOperation(kid.jLabel1);break;
                case 22: kid.jPanel1 = mutationOperation(kid.jPanel1);break;
                case 23: kid.jPanel2 = mutationOperation(kid.jPanel2);break;
                case 24: kid.jPanel3 = mutationOperation(kid.jPanel3);break;
                case 25: kid.jPanel4 = mutationOperation(kid.jPanel4);break;
                case 26: kid.jPanel5 = mutationOperation(kid.jPanel5);break;
            }
        }
        return kid;
    }
    public static Color mutationOperation(Color color){
        color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        return color;
    }
    public static KidsGA ClimbMountaion(KidsGA kid1,int component,int stepLength,int timeOfComponent){
        if (component>26)
            component = 26;
            KidsGA kid = kid1;
        switch (component){
                case 0: kid.mainFrameColor = mutationOperationClimbMountain(kid.mainFrameColor,timeOfComponent,stepLength);break;
                case 1: kid.jButton1 = mutationOperationClimbMountain(kid.jButton1,timeOfComponent,stepLength);break;
                case 2: kid.jButton2 = mutationOperationClimbMountain(kid.jButton2,timeOfComponent,stepLength);break;
                case 3: kid.jButton3 = mutationOperationClimbMountain(kid.jButton3,timeOfComponent,stepLength);break;
                case 4: kid.jButton4 = mutationOperationClimbMountain(kid.jButton4,timeOfComponent,stepLength);break;
                case 5: kid.jButton5 = mutationOperationClimbMountain(kid.jButton5,timeOfComponent,stepLength);break;
                case 6: kid.jButton6 = mutationOperationClimbMountain(kid.jButton6,timeOfComponent,stepLength);break;
                case 7: kid.jButton7 = mutationOperationClimbMountain(kid.jButton7,timeOfComponent,stepLength);break;
                case 8: kid.jButton8 = mutationOperationClimbMountain(kid.jButton8,timeOfComponent,stepLength);break;
                case 9: kid.jButton9 = mutationOperationClimbMountain(kid.jButton9,timeOfComponent,stepLength);break;
                case 10: kid.jButton10 = mutationOperationClimbMountain(kid.jButton10,timeOfComponent,stepLength);break;
                case 11: kid.jButton11 = mutationOperationClimbMountain(kid.jButton11,timeOfComponent,stepLength);break;
                case 12: kid.jButton12 = mutationOperationClimbMountain(kid.jButton12,timeOfComponent,stepLength);break;
                case 13: kid.jButton13 = mutationOperationClimbMountain(kid.jButton13,timeOfComponent,stepLength);break;
                case 14: kid.jButton14 = mutationOperationClimbMountain(kid.jButton14,timeOfComponent,stepLength);break;
                case 15: kid.jButton15 = mutationOperationClimbMountain(kid.jButton15,timeOfComponent,stepLength);break;
                case 16: kid.jButton16 = mutationOperationClimbMountain(kid.jButton16,timeOfComponent,stepLength);break;
                case 17: kid.jButton17 = mutationOperationClimbMountain(kid.jButton17,timeOfComponent,stepLength);break;
                case 18: kid.jButton18 = mutationOperationClimbMountain(kid.jButton18,timeOfComponent,stepLength);break;
                case 19: kid.jTextField1 = mutationOperationClimbMountain(kid.jTextField1,timeOfComponent,stepLength);break;
                case 20: kid.jTextField1TextColor = mutationOperationClimbMountain(kid.jTextField1TextColor,timeOfComponent,stepLength);break;
                case 21: kid.jLabel1 = mutationOperationClimbMountain(kid.jLabel1,timeOfComponent,stepLength);break;
                case 22: kid.jPanel1 = mutationOperationClimbMountain(kid.jPanel1,timeOfComponent,stepLength);break;
                case 23: kid.jPanel2 = mutationOperationClimbMountain(kid.jPanel2,timeOfComponent,stepLength);break;
                case 24: kid.jPanel3 = mutationOperationClimbMountain(kid.jPanel3,timeOfComponent,stepLength);break;
                case 25: kid.jPanel4 = mutationOperationClimbMountain(kid.jPanel4,timeOfComponent,stepLength);break;
                case 26: kid.jPanel5 = mutationOperationClimbMountain(kid.jPanel5,timeOfComponent,stepLength);break;
        }

        return kid;
    }
    public static Color mutationOperationClimbMountain(Color color,int timeOfComponent,int stepLength){
        int colorR = color.getRed();
        int colorG = color.getGreen();
        int colorB = color.getBlue();
        int i = (int) (Math.random()*3);
        switch (i){
            case 0:
                color = new Color((colorG+colorB)%256,(colorG+stepLength*timeOfComponent)%256,(colorB+stepLength*timeOfComponent)%256);
                break;
            case 1:
                color = new Color((colorR+stepLength*timeOfComponent)%256,(colorR+colorB)%256,(colorB+stepLength*timeOfComponent)%256);
                break;
            case 2:
                color = new Color((colorR+stepLength*timeOfComponent)%256,(colorG+stepLength*timeOfComponent)%256,(colorR+colorG)%256);
                break;
        }
        return color;
    }
    // Euclidean distance
    private static boolean judgeEuclideanDistance(KidsGA kidGA){
/*        int r1 = kidEA.jTextField1.getRed();
        int r2 = kidEA.jTextField1TextColor.getRed();
        int g1 = kidEA.jTextField1.getGreen();
        int g2 = kidEA.jTextField1TextColor.getGreen();
        int b1 = kidEA.jTextField1.getBlue();
        int b2 = kidEA.jTextField1TextColor.getBlue();
        System.out.println(r1+" "+r2+" "+g1+" "+g2+" "+b1+" "+b2);
*/
        int Eu =(int) Math.sqrt(Math.pow(kidGA.jTextField1.getRed()-kidGA.jTextField1TextColor.getRed(),2) + Math.pow(kidGA.jTextField1.getGreen()-kidGA.jTextField1TextColor.getGreen(),2)+Math.pow(kidGA.jTextField1.getBlue()-kidGA.jTextField1TextColor.getBlue(),2));

        System.out.println("Euclidean Distance:" + Eu);
        if(Eu >= 128)
            return true;
        else
            return false;
    }

    public static void main(String args[]) throws IOException {
        GA ga = new GA();
        ga.makeNewSolution();
        do {
            KidsGA test = mutation(populationGA.getLast());
            printColor(test);
        }while (!judgeEuclideanDistance(populationGA.getLast()));

    }

}

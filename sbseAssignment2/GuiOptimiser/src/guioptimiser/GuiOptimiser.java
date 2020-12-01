/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guioptimiser;

import Calculator.Calculator;
import com.sun.org.apache.xpath.internal.FoundIndex;
import javafx.scene.control.Cell;

import javax.print.attribute.standard.SheetCollate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Yixuan Liu & Mahmoud-Uni
 */
public class GuiOptimiser {

    private static String TARGET_APP = "calculator.jar";
    //private static final String TARGET_APP = "simpleApp.jar";
    private static final String TARGET_APP_COLOR = "color.csv";
    private static final int TARGET_APP_RUNNINGTIME = 1500;
    private static final String JAVA_COMMAND = "java -jar ";
    private static String parentDir = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int algorithm = 2; // 1 means Random Search, 2 means Genetic Algorithm, 3 means Climb Mountain
        int kidsNum = 2; // number of kids( for Genetic Algorithm)
        int times = 200; // experiment times
        int stepLength = 8; // for climb mountain
        float[] fitnessValue = new float[kidsNum];
        float bestFitness = 800;
        // first run the target app
        switch (args[0]) {
            case "calculator.jar":
                TARGET_APP = args[0].trim();
                System.out.println(args[0]);
                break;
            case "simpleApp.jar":
                TARGET_APP = args[0].trim();
                System.out.println(args[0]);
                break;
            default:
                System.out.println(args[0]);
                return;
        }
        parentDir = getParentDir();
        //System.out.println(parentDir.concat(TARGET_APP));

        switch (algorithm){
            case 1:
                for (int i = 0; i < times; i++) //RunTargetApp runTargetApp = new RunTargetApp(parentDir.concat(TARGET_APP), TARGET_APP_RUNNINGTIME);
                {
                    //runApp(parentDir.concat(TARGET_APP), TARGET_APP_RUNNINGTIME);
                    runApp(TARGET_APP, TARGET_APP_RUNNINGTIME);
                    changeColorAll();
                }
                break;
            case 2:
                GA op = new GA();
                String pathTemp = runApp(TARGET_APP, TARGET_APP_RUNNINGTIME);
                fitness.loadPixelsFromImage(new File(pathTemp));
                for(int i = 0; i < times; i++){
                    op.makeNewSolution();
                    for(int j = 0; j < kidsNum; j++) {
                        //               System.out.println(GA.populationGA.size());
                        GA.populationGA.add(GA.mutation(GA.populationGA.getFirst()));
                        newsaveToCSV(parentDir.concat(TARGET_APP_COLOR), GA.populationGA, j+1);
                        pathTemp = runApp(TARGET_APP, TARGET_APP_RUNNINGTIME);
                        fitnessValue[j] = fitness.loadPixelsFromImage(new File(pathTemp));
                        System.out.println("fitnessJ: "+fitnessValue[j]+"          bestfitness "+bestFitness);
                        if (fitnessValue[j] < bestFitness){
                            bestFitness  = fitnessValue[j];
                            newsaveToCSV("C://Users//84464//Desktop//sbse//sbse-as02-cust//bestSolution.csv", GA.populationGA, j);
                            System.out.println(pathTemp);
                            System.out.println("current best fitness: "+ bestFitness);

                        }else if(i==9||i==99||i==999){

                        }
                        else {
                            File image1 = new File("C://Users//84464//Desktop//sbse//sbse-as02-cust//" + pathTemp);
                            image1.delete();
            /*                File folder1 = new File("C://Users//84464//Desktop//sbse//sbse-as02-cust");
                            File[] files = folder1.listFiles();
                            for(File file:files){
                                if(file.getName().equals(pathTemp))
                                    file.delete();

                            }
            */
                        }
                    }
                    GA.populationGA.clear();
                }
                break;
            case 3:
                int focus = 0;
                int timeOfComponent = 0;
                GA op1 = new GA();
                op1.makeNewSolution();
                for(int t = 0; t < times; t++){
                    int component = (int) (t / (times/27));
                    if(focus == component)
                        timeOfComponent++;
                    else {
                        timeOfComponent = 1;
                        focus = component;
                    }
                    GA.ClimbMountaion(GA.populationGA.getFirst(),component,stepLength,timeOfComponent);

                    newsaveToCSV(parentDir.concat(TARGET_APP_COLOR), GA.populationGA, GA.populationGA.size()-1);
                    pathTemp = runApp(TARGET_APP, TARGET_APP_RUNNINGTIME);
                    int j = GA.populationGA.size();

                    fitnessValue[j-1] = fitness.loadPixelsFromImage(new File(pathTemp));
                    if (fitnessValue[j-1] < bestFitness){
                        bestFitness = fitnessValue[j-1];
                        newsaveToCSV("C://Users//84464//Desktop//sbse//sbse-as02-cust//bestSolution.csv", GA.populationGA, j-1);
                        System.out.println(pathTemp);
                        System.out.println("current best fitness: "+ bestFitness);
                    }else if(t==9||t==99||t==999){

                    }
                    else {
                        File image1 = new File("C://Users//84464//Desktop//sbse//sbse-as02-cust//" + pathTemp);
                        image1.delete();
                    }
                }
                break;
        }
    }
//"C://Users//84464//Desktop//sbse//sbse-as02-cust//sbseAssignment2//GuiOptimiser//color.csv"
    public static String runApp(String path, int targetAppRunningtime) {
        String pathTem = "";
        try {
            //java -jar C:\Users\Mahmoud-Uni\Documents\NetBeansProjects\calculator\dist\calculator.jar

            //path = "\""+path+"\"";
   //         System.out.println("Target App" + path);

            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec(JAVA_COMMAND.concat(path));
            try {
                Thread.sleep(targetAppRunningtime);
                Capture capture = new Capture();
                pathTem = capture.takeScreenShoot();
//                BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(process.getErrorStream()));
//                String line = "";
//                while((line=stdError.readLine())!=null)
//                {
//                    System.out.println("error!");
//                    System.out.println(line);
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
   //         System.out.println("Target App");
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathTem;
    }

    public static void changeColorAll() {
        try {
            // guiComponents contains GUI components' name.
            ArrayList<String> guiComponents = new ArrayList<>();
            guiComponents.add("mainFrameColor"); // both apps
            guiComponents.add("jButton1");// both apps
            guiComponents.add("jButton2");
            guiComponents.add("jButton3");
            guiComponents.add("jButton4");
            guiComponents.add("jButton5");
            guiComponents.add("jButton6");
            guiComponents.add("jButton7");
            guiComponents.add("jButton8");
            guiComponents.add("jButton9");
            guiComponents.add("jButton10");
            guiComponents.add("jButton11");
            guiComponents.add("jButton12");
            guiComponents.add("jButton13");
            guiComponents.add("jButton14");
            guiComponents.add("jButton15");
            guiComponents.add("jButton16");
            guiComponents.add("jButton17");
            guiComponents.add("jButton18");
            
            guiComponents.add("jTextField1");// both apps
            guiComponents.add("jTextField1TextColor");// both apps

            guiComponents.add("jLabel1");// both apps

            guiComponents.add("jPanel1");// both apps
            guiComponents.add("jPanel2");
            guiComponents.add("jPanel3");
            guiComponents.add("jPanel4");
            guiComponents.add("jPanel5");

            ArrayList<ArrayList<Integer>> RGB = new ArrayList<>();
            Random randomInt = new Random();

            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

            saveToCSV(parentDir.concat(TARGET_APP_COLOR), guiComponents, RGB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToCSV(String filePath, ArrayList<String> guiComponents, ArrayList<ArrayList<Integer>> RGB) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
            String line = "";
            for (int i = 0; i < guiComponents.size(); i++) {
                line += guiComponents.get(i).concat(",").concat(RGB.get(i).toString().replace("[", "").replace("]", "").replaceAll("\\s", "")) + "\n";
                //System.out.println(line);
            }
            br.write(line);
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newsaveToCSV(String filePath, LinkedList<KidsGA> kidGA, int numOfSon) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
            String line = "";
      //      for (int i = 0; i < numOfSon*27; i++) {
      //         line += "\n";
        //    }
      //      System.out.println("j : "+numOfSon+ " kidSize: "+kidGA.size());
            line += ("mainFrameColor,").concat(kidGA.get(numOfSon).mainFrameColor.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
  //          System.out.println(line);
            br.flush();
            line = "";
            line += ("jButton1,").concat(kidGA.get(numOfSon).jButton1.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton2,").concat(kidGA.get(numOfSon).jButton2.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton3,").concat(kidGA.get(numOfSon).jButton3.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton4,").concat(kidGA.get(numOfSon).jButton4.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton5,").concat(kidGA.get(numOfSon).jButton5.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton6,").concat(kidGA.get(numOfSon).jButton6.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton7,").concat(kidGA.get(numOfSon).jButton7.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton8,").concat(kidGA.get(numOfSon).jButton8.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton9,").concat(kidGA.get(numOfSon).jButton9.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton10,").concat(kidGA.get(numOfSon).jButton10.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton11,").concat(kidGA.get(numOfSon).jButton11.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton12,").concat(kidGA.get(numOfSon).jButton12.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton13,").concat(kidGA.get(numOfSon).jButton13.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton14,").concat(kidGA.get(numOfSon).jButton14.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton15,").concat(kidGA.get(numOfSon).jButton15.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton16,").concat(kidGA.get(numOfSon).jButton16.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton17,").concat(kidGA.get(numOfSon).jButton17.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jButton18,").concat(kidGA.get(numOfSon).jButton18.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jTextField1,").concat(kidGA.get(numOfSon).jTextField1.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jTextField1TextColor,").concat(kidGA.get(numOfSon).jTextField1TextColor.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jLabel1,").concat(kidGA.get(numOfSon).jLabel1.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jPanel1,").concat(kidGA.get(numOfSon).jPanel1.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jPanel2,").concat(kidGA.get(numOfSon).jPanel2.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jPanel3,").concat(kidGA.get(numOfSon).jPanel3.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jPanel4,").concat(kidGA.get(numOfSon).jPanel4.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            line = "";
            line += ("jPanel5,").concat(kidGA.get(numOfSon).jPanel5.toString().replace("java.awt.Color[r=", "").replace("g=", "").replace("b=", "").replace("]", "").replaceAll("\\s", "")) + "\n";
            br.write(line);
            br.flush();
            //System.out.println(line);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getParentDir() {
        String dir = "";
        try {
            File temp = new File("temp");
            dir = temp.getAbsolutePath().replace("temp", "");
            //System.out.println(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }
}
/*
kidGA.get(1).mainFrameColor+"";
            kidGA.jButton1+"";
            kidGA.jButton2+"";
            kidGA.jButton3+"";
            kidGA.jButton4+"";
            kidGA.jButton5+"";
            kidGA.jButton6+"";
            kidGA.jButton7+"";
            kidGA.jButton8+"";
            kidGA.jButton9+"";
            kidGA.jButton10+"";
            kidGA.jButton11+"";
            kidGA.jButton12+"";
            kidGA.jButton13+"";
            kidGA.jButton14+"";
            kidGA.jButton15+"";
            kidGA.jButton16+"";
            kidGA.jButton17+"";
            kidGA.jButton18+"";
            kidGA.jTextField1+"";
            kidGA.jTextField1TextColor+"";
            kidGA.jLabel1+"";
            kidGA.jPanel1+"";
            kidGA.jPanel2+"";
            kidGA.jPanel3+"";
            kidGA.jPanel4+"";
            kidGA.jPanel5+"";

*/
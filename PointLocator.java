/**
 * Created by andrewnyaisonga on 3/28/17.
 */
import java.util.*;

public class PointLocator {
    int switchResponse = 0;

    public void instruction(){
        System.out.println(" \n" + "To begin I am going to tell you how to use the program\n" +
                "The program will ask you for the number of lines that you wanna work with, Then you will have to enter the endpoints of the lines.\n" +
                "Every line has two end points with 4 numbers: the First two are (x,y) of one point and the last two are the other end point\n" +
                "You will enter them as follows x1 y1 x2 y2. So if you indicated you want 3 lines you will have 3 lines of points written in that way\n" +
                "Lastly the program will ask you of how many pairs of points you want to work with.\n"+"This program compares two points at a time and you will enter them the same way with the first two " +
                "indicating one point and the second two indicating the point to be compared\n" + "\n");
    }

    public Point[][] pComparison() {
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        int tempCounter = 0;
        System.out.println();
        try{
            System.out.println("Number of comparison of points: ");
            int numberPoints = scan.nextInt();
            System.out.println();
            System.out.println("Now enter the points, each line has a pair of points to be compared:");
            Double[][] pointsCompare = new Double[numberPoints][4] ;
            String[] string;

            for (int i = numberPoints; i > 0; i--) {
                String info = scan1.nextLine();
                string = info.split(" ");
                for(int j = 0; j < string.length; j++){
                    pointsCompare[tempCounter][j] = Double.parseDouble(string[j]);
                }
                tempCounter++;
            }

            Point[][] pointArray = new Point[numberPoints][2];

            try{
                for (int i = 0; i < pointsCompare.length; i++) {
                    Point temp1 = new Point(pointsCompare[i][0], pointsCompare[i][1]);
                    Point temp2 = new Point(pointsCompare[i][2], pointsCompare[i][3]);
                    pointArray[i][0] = temp1;
                    pointArray[i][1] = temp2;
                }
            }catch(NullPointerException e){
                System.out.println("Your line endpoints input is not right. PLEASE READ THE INSTRUCTIONS below");
                instruction();
                System.out.println("Lets's continue\n");
                pComparison();
            }
            return pointArray;

        }catch(InputMismatchException e){
            if(switchResponse == 0) {
                System.out.println("Please input an Integer :)\n");
                pComparison();
            }
            else{
                System.out.println("Integer please.");
                pComparison();
            }

        }
       return null;
    }

    public Segment[] makeLines() {

        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        int numberLines = 0;
        try {
            switch(switchResponse) {
                case 0 : System.out.println("How many lines do you wanna work with today:");
                            break;
                case 1: System.out.println("Enter the number of lines as an Integer: ");
                        break;
            }
            numberLines = scan.nextInt();
            Double[][] segments = new Double[numberLines][4];
            String[] string;
            System.out.println();
            if(switchResponse == 0) {
                System.out.print("Enter the endpoints of lines: (As instructed, Every line has 4 endpoints and one line points per line): \n");
            }
            else{
                System.out.println("Finally you got it\n");
                System.out.print("Please follow the instruction, Enter the endpoints of lines: (AS INSTRUCTED, Every line has 4 endpoints and one line points per line): \n");

            }
            int tempCounter = 0;
            for (int i = numberLines; i > 0; i--) {
                String cordInput = scan1.nextLine();
                string = cordInput.split(" ");
                for (int j = 0; j < string.length; j++) {
                    segments[tempCounter][j] = Double.parseDouble(string[j]);
                }
                tempCounter++;
            }
            Segment[] lineArray = new Segment[numberLines];
            try{
                for (int i = 0; i < lineArray.length; i++) {
                    lineArray[i] = new Segment(segments[i][0], segments[i][1], segments[i][2], segments[i][3]);
                }
            }catch(NullPointerException e ){
                System.out.println("Your line endpoints input is not right. PLEASE READ THE INSTRUCTIONS below\n");
                instruction();
                switchResponse = 1;
                System.out.println("Now start over\n");
                makeLines();
            }

            return lineArray;
        }
        catch (InputMismatchException a) {
            if(switchResponse == 0) {
                System.out.println("Please input an Integer :)\n");
            }
            else{
                System.out.println("Integer please.");
            }
        }
        switch(numberLines) {
            case 0:
                switchResponse = 1;
                makeLines();
                break;
            default:
                break;

        }
        return null;
    }

    public static void main(String[] args) {
        PointLocator pointLocator = new PointLocator();
        System.out.println("Welcome to the Point location program\n ");
        pointLocator.instruction();

        Segment[] lines = pointLocator.makeLines();         //Line segment
        Point[][] points = pointLocator.pComparison();      //All the point to be compared

        BST tree = new BST();
        MyTreeNode[] finall = new MyTreeNode[lines.length];

        for (int i = 0; i < lines.length; i++) {        //insert every line to the 2d-Tree
            tree.insert(lines[i]);
        }

        int compareCounter =1;
        for (int i = 0; i < points.length; i++) {           //point search position
            finall[i] = tree.search(tree.getNode(), points[i][0], points[i][1]);
            if (finall[i] == null) {
                System.out.println("For compare " + compareCounter+ ". No, they are not on the same plane.");
            }
            else if (finall[i] != null) {
                System.out.println("For compare " + compareCounter+ ". Yes, They are on the same plane.: " + finall[i].line.toString());
            }
            compareCounter++;
        }

        int externalNodes = tree.getNode().count();
        System.out.println("There are " + externalNodes + " external nodes");//counting the external nodes

    }

}


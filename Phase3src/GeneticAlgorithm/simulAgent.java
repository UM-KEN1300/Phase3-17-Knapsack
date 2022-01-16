package GeneticAlgorithm;

import java.util.ArrayList;

import javax.swing.JFrame;

import Components.Container;
import Components.parcelsABC;
import Components.parcelsLPT;
import Renderer.*;

// calculate the fitness based on first simulating the agent to place the pieces based on weights:-
// compactness, value to get, filled volume
public class simulAgent {
    private Container truck = new Container();
    private double[] values;
    private double[] chromosome;
    private int[] ammounts;
    private static final int aVolume = 16;
    private static final int bVolume = 24;
    private static final int cVolume = 27;
    private static final int lptVolume = 5;
    /**
     * Simulates an agent to fill a truck using the weights given in the chromosomes parameter 
     * @param values The values of each parcel type 
     * @param chromosome The agent's weights or the heuristics multipliers 
     * @param ammounts The amounts of each 
     */
    public simulAgent(double[] values, double[] chromosome, int[] ammounts) {
        this.values = values;
        this.chromosome = chromosome;
        this.ammounts = ammounts;
    }
    /**
     * Displays the solution after the agent has filled the container 
     */
    public void displaySol() {
        fillTruck();
        Display dis = new Display(truck.getShape());
        MainMenu.start(dis);
        ResultMenu r = new ResultMenu(truck);
    }
    /**
     * Method which gets the rating or fitness of this agent based on the value it gets after it fills the container 
     * @return
     */
    public int getRating() {
        return (int) getValue();
    }
    /**
     * Fills the trucks based on the agent's weights or its chromosome 
     */
    public void fillTruck() {
        for (int i = 0; i < truck.getLength(); i++) {
            for (int j = 0; j < truck.getHeight(); j++) {
                for (int k = 0; k < truck.getWidth(); k++) {
                    if (truck.getShape()[i][j][k] == -1) {
                        int[][][][] parcelData = new int[0][0][0][0];
                        int[] bestMove = tryToFill(i, j, k);
                        int colorID = 0;
                        switch (bestMove[0]) {
                            case 0:
                                parcelData = parcelsABC.parcelARot();
                                colorID = 0;
                                break;
                            case 1:
                                parcelData = parcelsABC.parcelBRot();
                                colorID = 1;
                                break;
                            case 2:
                                parcelData = parcelsABC.parcelCRot();
                                colorID = 2;
                                break;
                            case 3:
                                parcelData = parcelsLPT.parcelLRot();
                                colorID = 3;
                                break;
                            case 4:
                                parcelData = parcelsLPT.parcelPRot();
                                colorID = 4;
                                break;
                            case 5:
                                parcelData = parcelsLPT.parcelTRot();
                                colorID = 5;
                                break;
                        }
                        int[][][] parcelToAdd = parcelData[bestMove[1]];
                        if (truck.checkAvailable(parcelToAdd, i, j, k)) {
                            truck.add(parcelToAdd, i, j, k, colorID);
                        }
                        ammounts[bestMove[0]]--;
                    }
                }
            }
        }
    }
    /**
     * Tries to fill the container in the given coordinates with all the possible parcels and their rotations, will place 
     * the one with the highest rating 
     * @param i the z coordinate to be placed on 
     * @param j the y coordinate to be placed on 
     * @param k the x coordinate to be placed on 
     * @return an integer array containing the parcel to be placed and its rotation 
     */
    private int[] tryToFill(int i, int j, int k) {
        int[] bestMove = new int[2];
        int bestrating = -10000;
        int tempRating = -10000;
        for (int pType = 0; pType < 6; pType++) {
            int[][][][] parcelData = new int[0][0][0][0];
            int colorID = 0;
            switch (pType) {
                case 0:
                    if (ammounts[0] <= 0) {
                        break;
                    }
                    parcelData = parcelsABC.parcelARot();
                    colorID = 0;
                    break;
                case 1:
                    if (ammounts[1] <= 0) {
                        break;
                    }
                    parcelData = parcelsABC.parcelBRot();
                    colorID = 1;
                    break;
                case 2:
                    if (ammounts[2] <= 0) {
                        break;
                    }
                    parcelData = parcelsABC.parcelCRot();
                    colorID = 2;
                    break;
                case 3:
                    if (ammounts[3] <= 0) {
                        break;
                    }
                    parcelData = parcelsLPT.parcelLRot();
                    colorID = 3;
                    break;
                case 4:
                    if (ammounts[4] <= 0) {
                        break;
                    }
                    parcelData = parcelsLPT.parcelPRot();
                    colorID = 4;
                    break;
                case 5:
                    if (ammounts[5] <= 0) {
                        break;
                    }
                    parcelData = parcelsLPT.parcelTRot();
                    colorID = 5;
                    break;
            }
            for (int rot = 0; rot < parcelData.length; rot++) {
                int[][][] parcelRepresentation = parcelData[rot];
                if (truck.checkAvailable(parcelRepresentation, i, j, k)) {
                    truck.add(parcelRepresentation, i, j, k, colorID);
                    tempRating += chromosome[0] * getCompactness(truck.getShape());
                    tempRating += chromosome[1] * values[pType];
                    tempRating += chromosome[2] * getFilledVolume(truck.getShape());
                    tempRating += chromosome[3] * surfacesTouched(i, j, k, parcelRepresentation);
                    if (tempRating > bestrating) {
                        bestrating = tempRating;
                        bestMove[0] = pType;
                        bestMove[1] = rot;
                    }
                    truck.remove(parcelRepresentation, i, j, k);
                }
            }
        }
        return bestMove;
    }
    /**
     * Counts how many surfaces the parcel will touch if its placed there 
     * @param i the z coordinate 
     * @param j the y coordinate 
     * @param k the x coordinate 
     * @param parcelRep the parcel to be placed 
     * @return Returns the number of surfaces the parcel touched once it's placed there 
     */
    private int surfacesTouched(int i, int j, int k, int[][][] parcelRep) {
        int surfacesTouched = 0;
        if (i + parcelRep.length == 33) {
            surfacesTouched++;
        }
        if (i == 0) {
            surfacesTouched++;
        }
        if (j + parcelRep[0].length == 8) {
            surfacesTouched++;
        }
        if (j == 0) {
            surfacesTouched++;
        }
        if (k + parcelRep[0][0].length == 5) {
            surfacesTouched++;
        }
        if (k == 0) {
            surfacesTouched++;
        }
        return surfacesTouched;
    }
    /**
     * Calculates the compactness of the container after the parcel has been placed 
     * @param container the container which compactness is calculated for 
     * @return the value of its compactness 
     */
    private double getCompactness(int[][][] container) {
        double cumDist = 0;
        int voxelNo = 0;
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[0].length; j++) {
                for (int k = 0; k < container[0][0].length; k++) {
                    if (container[i][j][k] != -1) {
                        cumDist += Math.sqrt(Math.pow((i - container.length), 2)
                                + Math.pow((j - container[0].length), 2) + Math.pow((k - container[0][0].length), 2));
                        voxelNo++;
                    }
                }
            }
        }
        double compactness = cumDist / voxelNo;
        return compactness;
    }
    /**
     * The value of the truck/container based on the values array 
     * @return the total value of the truck/container 
     */
    private double getValue() {
        double result = 0;

        double aAdd = values[0] / aVolume;
        double bAdd = values[1] / bVolume;
        double cAdd = values[2] / cVolume;
        double lAdd = values[3] / lptVolume;
        double pAdd = values[4] / lptVolume;
        double tAdd = values[5] / lptVolume;

        for (int i = 0; i < truck.getShape().length; i++) {
            for (int j = 0; j < truck.getShape()[0].length; j++) {
                for (int k = 0; k < truck.getShape()[0][0].length; k++) {
                    switch (truck.getShape()[i][j][k]) {
                        case 0:
                            result += aAdd;
                            break;
                        case 1:
                            result += bAdd;
                            break;
                        case 2:
                            result += cAdd;
                            break;
                        case 3:
                            result += lAdd;
                            break;
                        case 4:
                            result += pAdd;
                            break;
                        case 5:
                            result += tAdd;
                            break;
                    }
                }
            }
        }
        return result;
    }
    /**
     * Calculates the number of cells which are filled (not empty)
     * @param container The container which the method is counting how empty it is for 
     * @return the number of cells filled
     */
    private int getFilledVolume(int[][][] container) {
        int filledSpace = 0;
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[0].length; j++) {
                for (int k = 0; k < container[0][0].length; k++) {
                    if (container[i][j][k] != -1) {
                        filledSpace++;
                    }
                }
            }
        }
        return filledSpace;
    }
}

package DLX;

import java.util.ArrayList;
import javax.swing.JFrame;

import Components.Container;
import Renderer.*;

public class DLXAlgorithm {
    private int[][] inputMatrix;
    private Container cont = new Container();
    private boolean parcelOrPent;
    private ArrayList<Header> headers = new ArrayList<>();
    private ArrayList<Integer> partialSolutions = new ArrayList<>();
    private ArrayList<Integer> bestSolution = new ArrayList<>();
    private Header root = new Header();
    private long startTime;
    private int timeLimit;
    private boolean findFirstSolution;

    // parcelorPent is true to work with ABC, else work with LPT
    // findFirstSolution is true to find the firstSolution else it finds the best
    // solution within the timeLimit object (only relevant for parcelLPT)
    // time limit is the time limit if you're working with ABC parcels or when
    // you're finding the best solution for parcel LPT

    public DLXAlgorithm(boolean parcelOrPent, boolean findFirstSolution, int timeLimit) {
        this.parcelOrPent = parcelOrPent;
        this.findFirstSolution = findFirstSolution;
        this.timeLimit = timeLimit;
    }
    /**
     * The driver which does DLX based on the parameters of the constructor 
     */
    public void driver() {
        startTime = System.currentTimeMillis();
        if (parcelOrPent) {
            inputMatrix = createInput.createInputMatrix(true);
            createDLXStructure();
            findbestPartialCover();
            updateUI(bestSolution);
            Display d = new Display(cont.getShape());
            MainMenu.start(d);
            ResultMenu a1 = new ResultMenu(cont);
        } else {
            inputMatrix = createInput.createInputMatrix(false);
            createDLXStructure();
            if (findFirstSolution) {
                findExactCover();
            } else {
                findBestSolution();
            }
            updateUI(bestSolution);
            Display d = new Display(cont.getShape());
            MainMenu.start(d);
            ResultMenu a1 = new ResultMenu(cont);
        }

    }

    /**
     * Creates the DLX structure by creating all the headers and nodes and linking them all together 
     */
    public void createDLXStructure() {
        Header prevHeader = root;
        for (int i = 0; i < inputMatrix[0].length; i++) {
            Header nextheader = new Header(i);
            headers.add(i, nextheader);
            prevHeader.linkHorizontally(nextheader);
            prevHeader = nextheader;
        }
        for (int i = 0; i < inputMatrix.length; i++) {
            Node prevNode = null;
            for (int j = 0; j < inputMatrix[0].length; j++) {
                if (inputMatrix[i][j] != -1) {
                    Header header = headers.get(j);
                    Node node = new Node(i, header);
                    header.size++;
                    if (prevNode == null) {
                        prevNode = node;
                    }
                    header.u.linkVertically(node);
                    prevNode.linkHorizontally(node);
                }
            }
        }

    }
    /**
     * Uses DLX to find the best solution within the time limit if ABC (non pentomino) parcels are chosen, exact cover solution can't be found 
     * so a partial cover solution is returned instead 
     */
    public void findbestPartialCover() {
        if (totalValue(partialSolutions) > totalValue(bestSolution)) {
            ArrayList<Integer> temp = new ArrayList<>(partialSolutions);
            bestSolution = temp;
        }
        Header h = getMinHeader();
        h.unlink();
        for (Node i = h.d; i != h; i = i.d) {
            partialSolutions.add(i.rowInput);
            for (Node j = i.r; j != i; j = j.r) {
                j.header.unlink();
            }
            findbestPartialCover();
            if (System.currentTimeMillis() - startTime >= timeLimit) {
                return;
            } else {
                System.out.print("\r loading...");
            }
            partialSolutions.remove(partialSolutions.size() - 1);
            for (Node k = i.l; k != i; k = k.l) {
                k.header.link();
            }
        }
        h.link();
    }
    /**
     * Uses DLX to find the first solution for an exact cover of the pentomino parcels 
     */
    public boolean findExactCover() {
        if (root.r == root) {
            bestSolution = partialSolutions;
            return true;
        }
        Header h = getMinHeader();
        h.unlink();
        for (Node i = h.d; i != h; i = i.d) {
            partialSolutions.add(i.rowInput);
            for (Node j = i.r; j != i; j = j.r) {
                j.header.unlink();
            }
            if (findExactCover()) {
                return true;
            }
            partialSolutions.remove(partialSolutions.size() - 1);
            for (Node k = i.l; k != i; k = k.l) {
                k.header.link();
            }
        }
        h.link();
        return false;
    }

    /**
     * Tries to find the best solution based on the total value of the container within the time limit in the parameter 
     * Group's answer to question C
     */
    public void findBestSolution() {
        if (root.r == root) {
             if (totalValue(partialSolutions) > totalValue(bestSolution)) {
                ArrayList<Integer> temp = new ArrayList<>(partialSolutions);
                bestSolution = temp;
            }
            return;
        }
        Header h = getMinHeader();
        h.unlink();
        for (Node i = h.d; i != h; i = i.d) {
            partialSolutions.add(i.rowInput);
            for (Node j = i.r; j != i; j = j.r) {
                j.header.unlink();
            }
            findBestSolution();
            if (System.currentTimeMillis() - startTime >= timeLimit) {
                return;
            } else {
                System.out.print("\rloading...");
            }
            partialSolutions.remove(partialSolutions.size() - 1);
            for (Node k = i.l; k != i; k = k.l) {
                k.header.link();
            }
        }
        h.link();
    }
    /**
     * Iterates through all the possible headers and finds the header with the smallest number of nodes (size)
     * @return the header with the smallest size
     */
    public Header getMinHeader() {
        int minValue = Integer.MAX_VALUE;
        Header minHeader = null;
        for (Header i = (Header) root.r; i != root; i = (Header) i.r) {
            if (i.size < minValue) {
                minHeader = i;
                minValue = i.size;
            }
            if (minValue == 0) {
                return minHeader;
            }
        }
        return minHeader;
    }
    /**
     * Updates the container's shape based on the parameter 
     * @param solutions an arrayList filled with the number of rows representing/making up the solution for an exact cover 
     */
    public void updateUI(ArrayList<Integer> solutions) {
        for (int i = 0; i < inputMatrix.length; i++) {
            if (solutions.contains(i)) {
                for (int j = 0; j < inputMatrix[0].length; j++) {
                    if (inputMatrix[i][j] != -1) {
                        int[] temp = getCoords(j);
                        cont.getShape()[temp[0]][temp[1]][temp[2]] = inputMatrix[i][j];
                    }
                }
            }
        }
    }
    /**
     * Method to find the total value of the container based on the rows of the 2D input matrix given to it in ArrayList parameter solutions 
     * @param solutions The rows of the 2D input matrix which the method will be looking at 
     * @return the total value of the container 
     */
    public int totalValue(ArrayList<Integer> solutions) {
        double finalRes = 0.0;
        for (int i = 0; i < inputMatrix.length; i++) {
            if (solutions.contains(i)) {
                for (int j = 0; j < inputMatrix[0].length; j++) {
                    if (inputMatrix[i][j] == -1) {
                        continue;
                    }
                    switch (inputMatrix[i][j]) {
                        case 0:
                            finalRes += (double) 3 / 16;
                            break;
                        case 1:
                            finalRes += (double) 4 / 24;
                            break;
                        case 2:
                            finalRes += (double) 5 / 27;
                            break;
                        case 3:
                            finalRes += (double) 3 / 5;
                            break;
                        case 4:
                            finalRes += (double) 4 / 5;
                            break;
                        case 5:
                            finalRes += (double) 5 / 5;
                            break;

                    }
                }
            }
        }
        return (int) Math.round(finalRes);
    }
    /**
     * get the coordinates of the sample array in the createInput class which has the specific integer in the parameter 
     * @param a the int for which the coordinates we are getting 
     * @return an array of the coordinates 
     */
    public static int[] getCoords(int a) {
        int[][][] sampleArray = createInput.sampleArray;
        for (int i = 0; i < sampleArray.length; i++) {
            for (int j = 0; j < sampleArray[0].length; j++) {
                for (int k = 0; k < sampleArray[0][0].length; k++) {
                    if (sampleArray[i][j][k] == a) {
                        return new int[] { i, j, k };
                    }
                }
            }
        }
        return new int[] { 0 };
    }
}

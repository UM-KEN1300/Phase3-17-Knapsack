package GeneticAlgorithm;

import java.util.Random;

import Renderer.ResultMenu;

public class Driver {
    public static int weights_length = 4;
    private static Random r = new Random();
    public int[] amounts = { 0,0 , 0, 0, 0, 0 };
    public double[] values = { 0, 0, 0,0, 0, 0 };
    public static int popSize = 100;
    public static int matingPoolSize = 8;
    public static double mutationRate = 0.26;

    /**
     * constructor for the driver class which runs the Genetic Algorithm, gets the best weights from the Genetic Algorithm and fills the container based on that weights 
     * @param amounts the amounts of each parceltype 
     * @param values the values of each parceltype 
     */
    public Driver(int[] amounts, double[] values) {
        this.amounts = amounts;
        this.values = values;
    }
    /**
     * Class which fills the truck and displays it 
     */
    public void displaySol (){
        simulAgent temp = new simulAgent(values, doGA(), amounts);
        temp.fillTruck();
        temp.displaySol();
    }
    /**
     * simulates the genetic algorithm to run for 150 generations (which we found is to be more than enough to converge and find the best solution possible)
     * @return
     */
    public double[] doGA() {
        Individual[] population = new Individual[popSize];
        for (int i = 0; i < popSize; i++) {
            double[] tempWeights = new double[weights_length];
            for (int j = 0; j < weights_length; j++) {
                tempWeights[j] = r.nextDouble();
            }
            population[i] = new Individual(tempWeights, values, amounts);
        }
        HeapSort.sort(population);
        double [] toReturn = new double [0];
        int gen = 0;
        double bestFitness = 0;
        while (gen<150) {
            Individual[] nextPop = new Individual[popSize];
            nextPop = selectionMethod(population);
            Individual nextBestIndividual = returnBest(nextPop);
            gen++;
            population = nextPop;
            bestFitness = nextBestIndividual.getFitness();
            toReturn = nextBestIndividual.getChromosome();
        }
        return toReturn;
    }
    /**
     * Same selection Method used from phase 2's genetic algorithm, runs a tournament to determine 
     * each individual of the next generation, saves the best one of the previous generation (mix of tournament and elitism)
     * @param oldPop the previous generation 
     * @return the newest generation
     */
    public Individual[] selectionMethod(Individual[] oldPop) {
        Individual[] toReturn = new Individual[oldPop.length];
        for (int i = 0; i < popSize - 1; i++) {
            int tempRand = r.nextInt(100);
            Individual[] tempParents = tournament(oldPop);
            Individual child = tempParents[0].crossOver(tempParents[1]);
            if (tempRand < mutationRate * 100) {
                child.mutateIndividual();
            }
            toReturn[i] = child;
        }
        toReturn[oldPop.length - 1] = returnBest(oldPop);
        return toReturn;
    }
    /**
     * Builds a pool of individuals based on the field matingPoolSize from the previous population randomly and selects the 2 best individuals from there 
     * @param oldPop the previous population/generation
     * @return the next population/generation
     */
    public Individual[] tournament(Individual[] oldPop) {
        Individual[] parents = new Individual[2];
        Individual[] matingPool = new Individual[matingPoolSize];
        for (int i = 0; i < matingPoolSize; i++) {
            int tempRand = r.nextInt(oldPop.length);
            matingPool[i] = oldPop[tempRand];
        }
        HeapSort.sort(matingPool);
        parents[0] = matingPool[0];
        parents[1] = matingPool[1];
        return parents;
    }

    public Individual returnBest(Individual[] oldPop) {
        HeapSort.sort(oldPop);
        return oldPop[0];
    }

}

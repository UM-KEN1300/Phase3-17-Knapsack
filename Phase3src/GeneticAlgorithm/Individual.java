package GeneticAlgorithm;
import Renderer.*;

import java.util.Random;

public class Individual {
    private double [] chromosome = new double [4]; 
    private double fitness = 0;
    private double [] values = new double [6];  
    private int [] amounts = new int [6];
    private Random rand = new Random();
	/**
	 * Constructor class for the Individual class 
	 * @param chromosome the weights which makes up the Individual's chromosomes 
	 * @param values the values of the parcelTypes 
	 * @param amounts the amounts of the parcelTypes 
	 */
    public Individual (double [] chromosome, double [] values, int [] amounts){
        this.chromosome = chromosome;
        this.values = values;
        this.amounts = amounts;
        this.fitness = calcFitness(chromosome);
    }
	/**
	 * Calculate the fitness of the individual based on simulating the agent filling the truck 
	 * @param chromosome the chromosome of the individual (the weights)
	 * @return the fitness of the individual which is the total value of the truck 
	 */
    private double calcFitness (double []chromosome){
        simulAgent fit = new simulAgent(values, chromosome, amounts);
		fit.fillTruck();
        return fit.getRating();   
    }

    public double[] getChromosome() {
		return chromosome;
	}
	
	public void setChromosome(double[] chromosome) {
		this.chromosome = chromosome;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
    public Individual clone() {
		double[] chromClone = new double[chromosome.length];
		for (int i = 0; i < chromClone.length; i++) {
			chromClone[i] = chromosome[i];
		}
		return new Individual(chromClone, values, amounts);
	}
	/**
	 * Crossover operator for the individual, uses single random point crossover 
	 * @param partner the partner which the Individual is gonna be crossovered with
	 * @return child individual resulting from the mating of the Individual partner and the Individual object 
	 */
	public Individual crossOver(Individual partner) {
		double[] child = new double[chromosome.length];
		
		for (int i = 0; i < child.length; i++) {
			int tempRand = rand.nextInt(2);
			if (tempRand == 1) {
				child[i] = chromosome[i];
			} else {
				child[i] = partner.getChromosome()[i];
			}
		}
		return new Individual(child, values, amounts);
	}
	/**
	 * Mutates the individual by changing one weight by either increaseing it by 0.35 or decreasing it by 0.35
	 */
	public void mutateIndividual() {
		int halfDice = rand.nextInt(2);
        int tempRand = rand.nextInt(chromosome.length);
		if(halfDice==1){
        chromosome[tempRand] +=0.35;}
		else {
			chromosome[tempRand]-=0.35;
		}
	}
}

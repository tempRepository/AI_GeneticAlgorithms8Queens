package pl.lodz.uni.math.GA8Queens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Population {

    private Random rand;
    private int greatness = 0;
    private List<Chromosome> chromosomes;
    private List<Chromosome> newChromosomes;
    private int chromosomeSize;

    public Population(int populationSize, int chromosomeSize) {
        this.chromosomeSize = chromosomeSize;
        rand = new Random();
        chromosomes = new ArrayList<Chromosome>();
        newChromosomes = new ArrayList<Chromosome>();//??? necessary?
        for (int i = 0; i < populationSize; i++) {
            chromosomes.add(new Chromosome(chromosomeSize));
        }

        calculateGreatness();
    }

    private boolean mutate(int chromosomeId) {
        if (chromosomeId >= 0 && chromosomeId < getChromosomes().size()) {
            boolean probability = rand.nextInt(5) == 0;
            if (probability) {
                getChromosomes().get(chromosomeId).mutate();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean hybridization(int firstChromosome, int secondChromosome) {
        boolean probability = rand.nextInt(5) == 0;
        if (probability) {

            int length = 0;
            while (length == 0) {
                length = rand.nextInt((chromosomeSize * chromosomeSize) + 1);
            }
            int positionA = -1;
            int positionB = -1;
            //   while (positionA == positionB) {
            positionA = rand.nextInt(chromosomeSize * chromosomeSize - (length - 1));
            positionB = rand.nextInt(chromosomeSize * chromosomeSize - (length - 1));
            //   }
            int[] genesA = getChromosomes().get(firstChromosome).getSingleDimensionalGenes();
            int[] genesB = getChromosomes().get(secondChromosome).getSingleDimensionalGenes();
            int[] firstChildrenGenes = new int[chromosomeSize * chromosomeSize];
            for (int i = 0; i < chromosomeSize * chromosomeSize; i++) {
                firstChildrenGenes[i] = genesA[i];
            }

            for (int i = 0; i < length; i++) {
                int geneB_InChildren_Position = getPosition(firstChildrenGenes, genesB[i + positionB]);
                int geneInChildrenCenter_Position = i + positionA;
                if (geneB_InChildren_Position != geneInChildrenCenter_Position) {

                    int temp = firstChildrenGenes[geneB_InChildren_Position];
                    firstChildrenGenes[geneB_InChildren_Position] = firstChildrenGenes[geneInChildrenCenter_Position];
                    firstChildrenGenes[geneInChildrenCenter_Position] = temp;


                }
            }
            Chromosome children1 = new Chromosome(chromosomeSize);
            children1.setSingleDimensionalGenes(firstChildrenGenes);
            newChromosomes.add(children1);
            //2 dziecko

            int[] secondChildrenGenes = new int[chromosomeSize * chromosomeSize];
            for (int i = 0; i < chromosomeSize * chromosomeSize; i++) {
                secondChildrenGenes[i] = genesB[i];
            }

            for (int i = 0; i <= length - 1; i++) {
                int geneA_InSecondChildrenPosition = getPosition(secondChildrenGenes, genesA[i + positionA]);
                int geneInSecondChildrenCenterPosition = i + positionB;
                if (geneA_InSecondChildrenPosition != geneInSecondChildrenCenterPosition) {

                    int temp = secondChildrenGenes[geneA_InSecondChildrenPosition];
                    secondChildrenGenes[geneA_InSecondChildrenPosition] = secondChildrenGenes[geneInSecondChildrenCenterPosition];
                    secondChildrenGenes[geneInSecondChildrenCenterPosition] = temp;
                }

            }

            Chromosome children2 = new Chromosome(chromosomeSize);
            children2.setSingleDimensionalGenes(secondChildrenGenes);
            newChromosomes.add(children2);
//            Chromosome chromosome1 = getChromosomes().get(firstChromosome);
//            Chromosome chromosome2 = getChromosomes().get(secondChromosome);
//            getChromosomes().remove(chromosome1);
//            getChromosomes().remove(chromosome2);
            return true;
        } else {
            newChromosomes.add(getChromosomes().get(firstChromosome));
            newChromosomes.add(getChromosomes().get(secondChromosome));
//            Chromosome chromosome1 = getChromosomes().get(firstChromosome);
//            Chromosome chromosome2 = getChromosomes().get(secondChromosome);
//            getChromosomes().remove(chromosome1);
//            getChromosomes().remove(chromosome2);
            return false;
        }
    }

    private void hybridizationForAll() {
        int counter = getChromosomes().size() / 2;
        for (int i = 0; i < counter; i++) {
            int parentA = -1;
            int parentB = -1;
            if (greatness > 0) {
                int sum = 0;
                int randomParent = rand.nextInt(greatness) + 1;
                int iterator = 0;
                while (sum < randomParent) {
                    sum += chromosomes.get(iterator).getRate();
                    iterator++;
                }
                parentA = iterator - 1;
                parentB = iterator - 1;
                if (greatness == 1) {
                    System.out.println("");
                }
                int criticalIterations = 5;
                while (parentA == parentB) {
                    if (criticalIterations > 0) {
                        sum = 0;
                        randomParent = rand.nextInt(greatness) + 1;
                        iterator = 0;
                        while (sum < randomParent) {
                            sum += chromosomes.get(iterator).getRate();
                            iterator++;
                        }

                        parentB = iterator - 1;
                        criticalIterations--;
                    } else {
                        while (parentA == parentB) {
                            parentB = rand.nextInt(chromosomes.size());

                        }

                    }
                }
                hybridization(parentA, parentB);
            } else {
                while (parentA == parentB) {
                    parentA = rand.nextInt(chromosomes.size());
                    parentB = rand.nextInt(chromosomes.size());

                }
                hybridization(parentA, parentB);
            }
            //hybridization(2*i, 2*i+1);
        }
    }

    private void newToTheOld() {
        chromosomes = newChromosomes;
        //important?
//        if (chromosomes.size()<=30) {
//          Collections.sort(chromosomes);  
//        }

        // int quantity = newChromosomes.size() / 2;
        newChromosomes = new ArrayList<Chromosome>();
        //w tym miejscu chromosomy w newChromosomes musza być już posortowane 
        //według funkcji oceny
//        for (int i = 0; i < quantity; i++) {
//            chromosomes.add(newChromosomes.get(i));
//
//        }
//        newChromosomes = new ArrayList<Chromosome>();
    }

    private boolean transposition(int chromosomeId) {
        Chromosome chromosomePointer = getChromosomes().get(chromosomeId);
        boolean probability = rand.nextInt(2) == 0;
        // boolean probability = true;
        if (probability) {
//losowanie działania
            int action = rand.nextInt(6);
            //int action = 6;
            if (action == 1) {
                //zamiana wiersza z wierszem
                int row1 = -1;
                int row2 = -1;
                while (row1 == row2) {
                    row1 = rand.nextInt(chromosomeSize);
                    row2 = rand.nextInt(chromosomeSize);
                }
                for (int i = 0; i < chromosomeSize; i++) {
                    int temp = chromosomePointer.getGenes()[row1][i];
                    chromosomePointer.getGenes()[row1][i] = chromosomePointer.getGenes()[row2][i];
                    chromosomePointer.getGenes()[row2][i] = temp;
                }

            } //zamiana kolumn
            else if (action == 2) {
                int col1 = -1;
                int col2 = -1;

                while (col1 == col2) {
                    col1 = rand.nextInt(chromosomeSize);
                    col2 = rand.nextInt(chromosomeSize);
                }
                for (int i = 0; i < chromosomeSize; i++) {
                    int temp = chromosomePointer.getGenes()[i][col1];
                    chromosomePointer.getGenes()[i][col1] = chromosomePointer.getGenes()[i][col2];
                    chromosomePointer.getGenes()[i][col2] = temp;
                }
            } //zamiana kolumn z wierszami
            else if (action == 3) {
                int col = -1;
                int row = -1;
                int[] tempCol = new int[chromosomeSize];
                int[] tempRow = new int[chromosomeSize];
                boolean same = false;
                while (!same) {

                    col = rand.nextInt(chromosomeSize);
                    row = rand.nextInt(chromosomeSize);
                    tempCol = new int[chromosomeSize];
                    tempRow = new int[chromosomeSize];
                    for (int i = 0; i < chromosomeSize; i++) {
                        tempCol[i] = chromosomePointer.getGenes()[i][col];
                        tempRow[i] = chromosomePointer.getGenes()[row][i];
                    }


                    for (int i = 0; i < tempCol.length && !same; i++) {
                        for (int j = 0; j < tempRow.length && !same; j++) {
                            if (tempCol[i] == tempRow[j] && i == j) {
                                same = true;
                            }
                        }
                    }

                }
                for (int i = 0; i < chromosomeSize; i++) {

                    chromosomePointer.getGenes()[i][col] = tempRow[i];
                    chromosomePointer.getGenes()[row][i] = tempCol[i];

                }
                chromosomePointer.getGenes();
            } //zamiana przekątnych z wierszem
            else if (action == 4) {
                int random = rand.nextInt(2);
                // int random =1;
                if (random == 0) {
                    //zamiana przekątnej 1
                    //  int diagonal = rand.nextInt(chromosomeSize);
                    int row = rand.nextInt(chromosomeSize);
                    int[] tempDiagonal = new int[chromosomeSize];
                    int[] tempRow = new int[chromosomeSize];
                    for (int i = 0; i < chromosomeSize; i++) {
                        tempDiagonal[i] = chromosomePointer.getGenes()[i][i];
                        tempRow[i] = chromosomePointer.getGenes()[row][i];
                    }

                    for (int i = 0; i < chromosomeSize; i++) {

                        chromosomePointer.getGenes()[i][i] = tempRow[i];
                        chromosomePointer.getGenes()[row][i] = tempDiagonal[i];
                    }
                } else {
                    //zamiana przekątnej 2
                    //  int diagonal = rand.nextInt(chromosomeSize);
                    int row = rand.nextInt(chromosomeSize);
                    int[] tempDiagonal = new int[chromosomeSize];
                    int[] tempRow = new int[chromosomeSize];
                    for (int i = 0; i < chromosomeSize; i++) {
                        tempDiagonal[i] = chromosomePointer.getGenes()[(chromosomeSize - 1) - i][i];
                        tempRow[i] = chromosomePointer.getGenes()[row][i];
                    }

                    for (int i = 0; i < chromosomeSize; i++) {

                        chromosomePointer.getGenes()[(chromosomeSize - 1) - i][i] = tempRow[i];
                        chromosomePointer.getGenes()[row][i] = tempDiagonal[i];
                    }
                }

//zamiana przekątnych z kolumnami
            } else if (action == 5) {
                int random = rand.nextInt(2);
                // int random = 1;
                if (random == 0) {
                    //zamiana przekątnej 1
                    //  int diagonal = rand.nextInt(chromosomeSize);
                    int col = rand.nextInt(chromosomeSize);
                    int[] tempDiagonal = new int[chromosomeSize];
                    int[] tempCol = new int[chromosomeSize];
                    for (int i = 0; i < chromosomeSize; i++) {
                        tempDiagonal[i] = chromosomePointer.getGenes()[i][i];
                        tempCol[i] = chromosomePointer.getGenes()[i][col];
                    }

                    for (int i = 0; i < chromosomeSize; i++) {

                        chromosomePointer.getGenes()[i][i] = tempCol[i];
                        chromosomePointer.getGenes()[i][col] = tempDiagonal[i];
                    }
                } else {
                    //zamiana przekątnej 2
                    //  int diagonal = rand.nextInt(chromosomeSize);
                    //!!!!!
                    int col = rand.nextInt(chromosomeSize);
                    int[] tempDiagonal = new int[chromosomeSize];
                    int[] tempCol = new int[chromosomeSize];
                    for (int i = 0; i < chromosomeSize; i++) {
                        tempDiagonal[i] = chromosomePointer.getGenes()[i][(chromosomeSize - 1) - i];
                        tempCol[i] = chromosomePointer.getGenes()[i][col];
                    }

                    for (int i = 0; i < chromosomeSize; i++) {

                        chromosomePointer.getGenes()[i][(chromosomeSize - 1) - i] = tempCol[i];
                        chromosomePointer.getGenes()[i][col] = tempDiagonal[i];
                    }
                }


            } //zamiana przekątnych
            else if (action == 6) {
                //int random = rand.nextInt(2);

                //zamiana przekątnej 1
                //  int diagonal = rand.nextInt(chromosomeSize);

                int[] tempDiagonal1 = new int[chromosomeSize];
                int[] tempDiagonal2 = new int[chromosomeSize];
                for (int i = 0; i < chromosomeSize; i++) {
                    tempDiagonal1[i] = chromosomePointer.getGenes()[i][i];
                    tempDiagonal2[i] = chromosomePointer.getGenes()[i][(chromosomeSize - 1) - i];
                }

                for (int i = 0; i < chromosomeSize; i++) {

                    chromosomePointer.getGenes()[i][i] = tempDiagonal2[i];
                    chromosomePointer.getGenes()[i][(chromosomeSize - 1) - i] = tempDiagonal1[i];
                }

            }

            return true;
        } else {
            return false;
        }
    }

    protected int makeNewPopulation() {
        hybridizationForAll();
        newToTheOld();
        boolean best = false;
        int bestId = -1;
        for (int i = 0; i < getChromosomes().size() && !best; i++) {
            mutate(i);
            transposition(i);
            int rating = getChromosomes().get(i).estimate();
            if (rating == (2 * chromosomeSize) + 2) {
                best = true;
                bestId = i;
            }
        }

        calculateGreatness();
        return bestId;

    }

    private void calculateGreatness() {
        greatness = 0;
        for (int i = 0; i < getChromosomes().size(); i++) {
            greatness += getChromosomes().get(i).getRate();

        }
    }

    private int getPosition(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the greatness
     */
    public int getGreatness() {
        return greatness;
    }

    /**
     * @return the chromosomes
     */
    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }
}
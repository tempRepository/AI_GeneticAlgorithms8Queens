package pl.lodz.uni.math.GA8Queens;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Chromosome implements Comparable{

    Random rand = new Random();
    private int size;
    private int[][] genes;
    private int rate;

    public Chromosome(int size) {
        this.rate = 0;
        this.size = size;
        genes = new int[size][size];
        List<Integer> tempList = new ArrayList<Integer>();
        for (int i = 1; i <= size * size; i++) {
            tempList.add(i);
        }
        Collections.shuffle(tempList);

        Integer[] tempArray2 = tempList.toArray(new Integer[0]);
        int tempCounter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                genes[i][j] = tempArray2[tempCounter];
                tempCounter++;
            }
        }
        estimate();
    }

    public Chromosome(int[][] genes, int size) {

        this.rate = 0;
        this.size = size;
        this.genes = genes;
        estimate();

    }

    public int estimate() {
        int tempSum;
        int tempRate = 0;
        int sumOfElements = (getSize() * ((getSize() * getSize()) + 1)) / 2;
        for (int i = 0; i < getSize(); i++) {
            tempSum = 0;
            for (int j = 0; j < getSize(); j++) {
                tempSum += getGenes()[i][j];
            }
            if (tempSum == sumOfElements) {
                tempRate++;
            }
        }

        for (int i = 0; i < getSize(); i++) {
            tempSum = 0;
            for (int j = 0; j < getSize(); j++) {
                tempSum += getGenes()[j][i];
            }
            if (tempSum == sumOfElements) {
                tempRate++;
            }
        }
        tempSum = 0;
        for (int i = 0; i < getSize(); i++) {
            tempSum += getGenes()[i][i];
        }
        if (tempSum == sumOfElements) {
            tempRate++;
        }

        tempSum = 0;
        int j = getSize() - 1;
        for (int i = 0; i < getSize(); i++) {
            tempSum += getGenes()[i][j];
            j--;

        }
        if (tempSum == sumOfElements) {
            tempRate++;
        }
        rate = tempRate;
        return tempRate;

    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                temp += getGenes()[i][j] + "|";
            }
            temp += "\n";
        }
        return temp;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    protected void mutate() {

        int a = -1;
        int b = -1;
        int c = -1;
        int d = -1;
        while (a == b && c == d) {
            a = rand.nextInt(size);
            b = rand.nextInt(size);
            c = rand.nextInt(size);
            d = rand.nextInt(size);
        }
        int temp = getGenes()[a][b];
        getGenes()[a][b] = getGenes()[c][d];
        getGenes()[c][d] = temp;
    }

    protected int[] getSingleDimensionalGenes() {
        int[] temp = new int[size * size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[counter] = getGenes()[i][j];
                counter++;
            }
        }
        return temp;
    }

    protected void setSingleDimensionalGenes(int[] singleDimensionalGenes) {
        int[] temp = new int[size * size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                getGenes()[i][j] = singleDimensionalGenes[counter];
                counter++;
            }
        }
    }

    /**
     * @return the genes
     */
    public int[][] getGenes() {
        return genes;
    }

    /**
     * @param genes the genes to set
     */
    public void setGenes(int[][] genes) {
        this.genes = genes;
    }

    @Override
    public int compareTo(Object o) {
        Chromosome temp= (Chromosome) o;
        if (temp.getRate()>=rate) {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}

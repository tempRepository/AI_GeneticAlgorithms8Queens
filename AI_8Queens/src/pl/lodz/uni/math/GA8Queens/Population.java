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
    int boardSize=8;
    private int greatness = 0;
    private List<Board> chessBoards;
    private List<Board> newChessBoards;
  

    public Population(int populationSize) {
        rand = new Random();
        chessBoards = new ArrayList<Board>();
        newChessBoards = new ArrayList<Board>();//??? necessary?
        for (int i = 0; i < populationSize; i++) {
            chessBoards.add(new Board());
        }

        computeGreatness();
    }

    private boolean mutate(int chromosomeId) {
        if (chromosomeId >= 0 && chromosomeId < getChessBoards().size()) {
            boolean probability = rand.nextInt(5) == 0;
            if (probability) {
                getChessBoards().get(chromosomeId).mutate();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    //change to private!
    //Bug here!
    public boolean crossover(int firstBoard, int secondBoard) {
        //zmienić!
       // boolean probability = rand.nextInt(5) == 0;
        boolean probability = true;
        if (probability) {

            int length = 0;
            while (length == 0) {
                length = rand.nextInt((boardSize) + 1);
            }
            int position = -1;          
            position = rand.nextInt(boardSize - (length - 1));

            int[] firstChild = chessBoards.get(firstBoard).getTiles().clone();
            int[] secondChild = chessBoards.get(secondBoard).getTiles().clone();

            for (int i = 0; i < length; i++) {
                int temp=secondChild[position+i];
                secondChild[position+i]=firstChild[position+i];
                firstChild[position+i]=temp;
            }
            Board children1 = new Board();
            children1.setTiles(firstChild);
            newChessBoards.add(children1);
            //2 dziecko
            Board children2 = new Board();
            children2.setTiles(secondChild);
            newChessBoards.add(children2);
            return true;
        } else {
            newChessBoards.add(getChessBoards().get(firstBoard));
            newChessBoards.add(getChessBoards().get(secondBoard));
            return false;
        }
    }

    private void crossoverForAll() {
        int counter = getChessBoards().size() / 2;
        for (int i = 0; i < counter; i++) {
            int parentA = -1;
            int parentB = -1;
            if (greatness>0) {
                int sum = 0;
                int randomParent = rand.nextInt(greatness) + 1;
                int iterator = 0;
                while (sum < randomParent) {
                    sum += chessBoards.get(iterator).getRate();
                    iterator++;
                }
                parentA = iterator - 1;
                parentB = iterator - 1;
            
                int criticalIterations = 100;
                while (parentA == parentB) {
                    if (criticalIterations > 0) {
                        sum = 0;
                        randomParent = rand.nextInt(greatness) + 1;
                        iterator = 0;
                        while (sum < randomParent) {
                            sum += chessBoards.get(iterator).getRate();
                            iterator++;
                        }

                        parentB = iterator - 1;
                        criticalIterations--;
                    } else {
                        while (parentA == parentB) {
                            parentB = rand.nextInt(chessBoards.size());

                        }

                    }
                }
                crossover(parentA, parentB);
            } else {
                while (parentA == parentB) {
                    parentA = rand.nextInt(chessBoards.size());
                    parentB = rand.nextInt(chessBoards.size());

                }
                crossover(parentA, parentB);
            }
         
        }
    }

    
    private void newToTheOld() {
        chessBoards = newChessBoards;
        //Collections.sort(chessBoards);  

        // int quantity = newChromosomes.size() / 2;
        newChessBoards = new ArrayList<Board>();
        //w tym miejscu chromosomy w newChromosomes musza być już posortowane 
        //według funkcji oceny
//        for (int i = 0; i < quantity; i++) {
//            chromosomes.add(newChromosomes.get(i));
//
//        }
//        newChromosomes = new ArrayList<Chromosome>();
    }


    protected int makeNewPopulation() {
        crossoverForAll();
        newToTheOld();
        boolean best = false;
        int bestId = -1;
        for (int i = 0; i < getChessBoards().size() && !best; i++) {
            mutate(i);
           // transposition(i);
            int rating = getChessBoards().get(i).estimate();
            if (rating == 8) {
                best = true;
                bestId = i;
            }
        }
        computeGreatness();
        return bestId;

    }

    private void computeGreatness() {
        greatness = 0;
        for (int i = 0; i < getChessBoards().size(); i++) {
            greatness += getChessBoards().get(i).getRate();

        }
        this.greatness=greatness;
    }

/*    private int getPosition(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i;
            }
        }
        return -1;
    }*/

    /**
     * @return the greatness
     */
    public int getGreatness() {
        return greatness;
    }

    /**
     * @return the chromosomes
     */
    public List<Board> getChessBoards() {
        return chessBoards;
    }
}
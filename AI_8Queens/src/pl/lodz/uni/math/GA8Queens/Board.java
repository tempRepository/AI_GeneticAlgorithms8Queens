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
public class Board implements Comparable {

    Random rand = new Random();
    private int size = 8;
    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int[] getTiles() {
        return tiles;
    }

    public void setTiles(int[] tiles) {
        this.tiles = tiles;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private int[] tiles;
    private int rate = 0;

    public Board() {

        tiles = new int[size];
        List<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            tempList.add(i);
        }
        Collections.shuffle(tempList);

        for (int i = 0; i < size; i++) {

            tiles[i] = tempList.get(i);
        }
        estimate();
    }

    public Board(int[] tiles, int size) {

        this.tiles = tiles;
        estimate();

    }

    public int estimate() {
        int tempRate = 0;
        boolean goodQueen = true;
        for (int i = 0; i < tiles.length && goodQueen; i++) {
            for (int j = 0; j < tiles.length && goodQueen; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (tiles[i] == tiles[j]
                            || Math.abs(i - j) == Math.abs(tiles[i] - tiles[j])) {
                        goodQueen = false;
                    }
                }
            }
            if (goodQueen) {
                tempRate++;
            }
        }
        this.rate = tempRate;
        return tempRate;

    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (tiles[i]==j) {
                    temp+="Q|";
                }
                else {
                    temp+="E|";
                }
                
          
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
      int temp = rand.nextInt(8);
      int value=rand.nextInt(8);
      tiles[temp]=value;
    }



    @Override
    public int compareTo(Object o) {
        Board temp = (Board) o;
        if (temp.getRate() >= rate) {
            return 1;
        } else {
            return -1;
        }
    }
}

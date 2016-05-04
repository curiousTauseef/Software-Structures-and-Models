
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class Main2 {
    public static void main (String[] args){
        BinSTree<Integer> binSTree = new BinSTree<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Random random = new Random();
        int[] intArray = new int[1000000];
        
        for (int i = 0; i < 1000000; i++) {
            int num = random.nextInt(1000000);
            binSTree.insert_to_tree(num);
            treeSet.add(num);
            treeMap.put(num, num);
        }
        
        for (int i = 0; i < 1000000; i++) {
            intArray[i] = random.nextInt(1000000);
        }
        
        Long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            binSTree.isInTree(intArray[i]);
        }
        Long finishTime = System.nanoTime();
        
        System.out.println("lab8 tree time:" + (finishTime - startTime) + "nano secs");
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeSet.contains(intArray[i]);
        }
        finishTime = System.nanoTime();
        System.out.println("TreeSet:" + (finishTime - startTime) + "nano secs");
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeMap.containsKey(intArray[i]);
        }
        finishTime = System.nanoTime();
        System.out.println("TreeMap:" + (finishTime - startTime) + "nano secs");
        
    }
}

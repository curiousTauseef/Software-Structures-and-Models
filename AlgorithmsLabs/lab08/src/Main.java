
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class Main {
    
    public static void main (String[] args){
        BinSTree<Integer> binSTree = new BinSTree<Integer>();
        Random random = new Random();
        
        for (int i = 0; i < 15; i++) {
            binSTree.insert_to_tree(random.nextInt(15));
        }
        
        binSTree.print();
        
        for (int i = 0; i < 15; i++) {
            binSTree.isInTree(i);
        }
        
    }
    
}

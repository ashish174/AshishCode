package algo.practice.tree.binarytree;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BinaryTree {
    Node root;

    public static void main(String[] args) {
        for(int i = 0; i < 50; i++){
            System.out.println(getRandomRatingIndex());

        }

    }

    public static int getRandomRatingIndex(){
        return (int)(Math.random() * 20 + 1);
    }

}

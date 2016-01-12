/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.msolutions.treeandlist;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author stech_000
 */
public class TestTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Node tree = new Node("root");
        Node node = tree.addChild(new Node("node"));
        Node secondNode = tree.addChild(new Node("secondNode"));
        Node subNode = node.addChild(new Node("subNode"));
        Node nodeLeaf = subNode.addChild(new Node("subNodeLeaf"));
        
        System.out.println(tree.height());
        // TODO code application logic here
    }
    
    public static <T> List<T> removeNSequentialOccurences(int n, List<T> list) {
        
        T tElem = null;
        int occurenceIndex = -1;
        int occurenceCount = 1;
        int i = 0;
        
        while (i < list.size()) {
            if (Objects.equals(tElem, list.get(i))) {
                occurenceCount++;
                if (occurenceCount >= n) {
                    if (occurenceIndex < 0) {
                        occurenceIndex = i;
                    }
                    i=occurenceIndex-1; // move index back on 1 position before clearing the sequence of equal elements
                    list.subList(occurenceIndex, occurenceIndex + occurenceCount).clear();
                    
                }
            } else {
                tElem = list.get(i);
                occurenceCount = 1;
                occurenceIndex = i;
            }
            i++;
        }
        return list;
    }
    
    public static <T> List<T> removeRandomOccurences(int n, List<T> list) {
        final Set<T> repeatedNumbers = list.parallelStream().distinct()//uniques
                .filter(number -> list.parallelStream()
                        .filter( //filter elements that do not occur more than n times
                                element -> Objects.equals(element, number)
                        ).count() >= n
                ).collect(Collectors.toSet());        
        
        list.removeAll(repeatedNumbers);
        return list;
    }
    
}

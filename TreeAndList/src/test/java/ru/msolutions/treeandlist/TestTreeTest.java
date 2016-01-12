/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.msolutions.treeandlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stech_000
 */
public class TestTreeTest {
    
    private List<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,2,1,2,2,2,5,5,5,4,5,3,2,6,7,8,9));
    
    public TestTreeTest() {
    }
    @Test
    public void testRemoveNSequentialOccurences() {
        System.out.println("removeNSequentialOccurences");
        List expResult = Arrays.asList(1,2,3,2,1,4,5,3,2,6,7,8,9);
        List result = TestTree.removeNSequentialOccurences(3,intList);
        assertEquals(expResult, result);
    } 

    /**
     * Test of removeRandomOccurences method, of class TestTree.
     */
    @Test
    public void testRemoveRandomOccurences() {
        System.out.println("removeRandomOccurences");
        List expResult = Arrays.asList(1,3,1,4,3,6,7,8,9);
        List result = TestTree.removeRandomOccurences(3,intList);
        assertEquals(expResult, result);
    }
    
}

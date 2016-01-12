/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.msolutions.treeandlist;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stech_000
 */
public class Node {
    
    private Node parent;
    
    private List<Node> children = new ArrayList<>();
    
    private String payload;

    public Node(String payload) {
        this.payload = payload;        
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }
    
    public Node addChild(Node node){  
        node.setParent(this);
        this.getChildren().add(node);        
        return node;
    }
    
    
    
    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
    
    public int height(){
        
        int maxHeight = 0;
        
        
        for(Node node : this.getChildren()){
            int height = node.height();
            if(height > maxHeight){
                maxHeight = height;
            }
        }
        return maxHeight +1;
    }
    
}

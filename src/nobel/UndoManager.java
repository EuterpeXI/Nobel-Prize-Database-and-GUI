/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

import java.util.*;
/**
 *
 * @author pbord
 */
public class UndoManager {
    Stack<ArrayList<Person>> undoStack;
    
    UndoManager(){
        undoStack = new Stack<>();
    }
    
    public void add(ArrayList<Person> database){
        undoStack.add(database);
    }
    
    public ArrayList<Person> undo(){
        if (canUndo()) {
            return undoStack.pop();
        }
        return undoStack.peek();
    }
    
    public ArrayList<Person> flush(){
        while (undoStack.size() > 1){
            undoStack.pop();
        }
        return undoStack.peek();
    }
    
    private boolean canUndo(){
        return undoStack.size() > 0;
    }
}
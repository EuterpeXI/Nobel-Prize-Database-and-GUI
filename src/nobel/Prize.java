/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

import java.time.*;
import java.util.*;
/**
 *
 * @author pbord
 */
public class Prize {
    int year;
    String motivation;
    ArrayList<Affiliation> affiliations = new ArrayList<>();
    int share;
    String category;
    
    Prize(int y, String m, ArrayList<Affiliation> a, int i, String c){
        year = y;
        motivation = m;
        affiliations = a;
        share = i;
        category = c;
    }
    
    //getters
    
    public String getYear(){
        return Integer.toString(year);
    }
    
    public String getMotivation(){
        return motivation;
    }
    
    public String getShare(){
        return Integer.toString(share);
    }
    
    public String category(){
        return category;
    }
}

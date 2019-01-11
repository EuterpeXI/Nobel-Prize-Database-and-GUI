/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

/**
 *
 * @author pbord
 */
public class Affiliation {
    String country;
    String city;
    String name;
    
    Affiliation(String _country, String _city, String _name){
        country = _country;
        city = _city;
        name = _name;
    }
    
    //getters
    
    public String getCountry(){
        return country;
    }
    
    public String getCity(){
        return city;
    }
        
    public String getName(){
        return name;
    }
    
    public String getAffiliations(){
        return country + " " + city + " " + name;
    }
}

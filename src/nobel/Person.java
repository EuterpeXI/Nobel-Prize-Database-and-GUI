package nobel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.*;
import java.util.*;
import java.text.Normalizer;
/**
 *
 * @author pbord
 */
public class Person {
    int id;
    String firstName;
    String bornCountry;
    String gender;
    LocalDate born;
    String diedCountry;
    String bornCity;
    LocalDate died;
    String bornCountryCode;
    String diedCity;
    String surname;
    String diedCountryCode;
    ArrayList<Prize> prizes = new ArrayList<>();
    String jpegURL;
    boolean repeatedSurname = false;
    
    //raw constructor - will update later
    Person(){
        
    };
    
   //toString
    public String normalizeLastName(String inputString) {
        StringBuilder editedString = new StringBuilder();
        inputString = Normalizer.normalize(inputString, Normalizer.Form.NFC);
        Scanner sc = new Scanner(inputString);
        String scannerToken = new String();
        char letterToAdd;
        String lastName, stringToEdit, juniorName;
        int lastNameFlag = 0;
        int juniorNameFlag = 0;
        lastName="";
        juniorName = "";
        //this is for people with ,'s in their last name
        while (sc.hasNext()==true) {
            scannerToken=sc.next();
            juniorName = scannerToken;
            if (lastNameFlag==0 && scannerToken.endsWith(",")) {
                lastNameFlag = 1;
                lastName = scannerToken;
            } 
            if (juniorNameFlag==0 && (scannerToken.compareToIgnoreCase("Jr.")==0) || (scannerToken.compareToIgnoreCase("Jr")==0)) {
                scannerToken=juniorName;
                juniorNameFlag = 1;
                
            }
        }
        
        stringToEdit=scannerToken;
        
        if (repeatedSurname==true) {
            if (lastNameFlag==1) {
                stringToEdit=lastName;
            }
            stringToEdit=firstName+"-"+lastName;
        }

        
        //EXCEPTIONS
        
        if (id==472) {
            return("balluet");
        }
        if (id==5) {
            return("pierre-curie");
        }
        if (id==6) {
            return ("marie-curie");
        }
        
        
        for (int x = 0; x< stringToEdit.length(); x++) {
            letterToAdd = stringToEdit.charAt(x);
            switch (letterToAdd) {
                case '\u00c0': editedString = editedString.append("a");
                               break;
                case '\u00e1': editedString = editedString.append("a");
                               break;
                case '\u00e4': editedString = editedString.append("a");
                               break;
                case '\u00e8': editedString = editedString.append("e");
                               break;
                case '\u00e9': editedString = editedString.append("e");
                               break;
                case '\u00ed': editedString = editedString.append("i");
                               break;
                case '\u00d5': editedString = editedString.append("o");
                               break;
                case '\u00f3': editedString = editedString.append("o");
                               break;
                case '\u00f6': editedString = editedString.append("o");
                               break;
                case '\u00f8': editedString = editedString.append("o");
                               break;
                case '\u014c': editedString = editedString.append("o");
                               break;
                case '\u014d': editedString = editedString.append("o");
                               break;
                case '\u00fc': editedString = editedString.append("o");
                               break;
                //case '-':      break;
                case '.':      break;
                case '\'':     break;
                case ')':      break;
                case ',':      break;
                
                default:       editedString = editedString.append(letterToAdd);
            }
        }
        return( editedString.toString());
    }
    
    public String setjpegURL () {
        StringBuilder builtURL = new StringBuilder();
        String award;
        String year;
        award = this.prizes.get(0).category;
        year = this.prizes.get(0).getYear();
        if (gender.equals("org")) {
            return("file:///C:../nobel/image/NA.png");
        }
        builtURL.append("https://www.nobelprize.org/nobel_prizes/");
        builtURL.append(award);
        builtURL.append("/laureates/");
        builtURL.append(year);
        builtURL.append("/");
        builtURL.append(normalizeLastName(this.surname).toLowerCase());
        builtURL.append(".jpg");
        return (builtURL.toString());
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Name and Gender: ");
        s.append(firstName);
        s.append(" ");
        s.append(surname);
        s.append(" ");
        s.append(gender);
        s.append("\n");
        s.append("Born Information: ");
        s.append(bornCity);
        s.append(" ");
        s.append(bornCountry);
        s.append(" ");
        s.append(born);
        s.append("\n");
        s.append("Died Info: ");
        s.append(diedCity);
        s.append(" ");
        s.append(diedCountry);
        s.append(" ");
        s.append(died);
        s.append("\n");
        s.append("Prize Year: ");
        s.append(prizes.get(0).year);
        s.append("\nMotivation: ");
        s.append(prizes.get(0).motivation);
        s.append("\n");
        return s.toString();
    }
    
    public String getFirstName(){
        return "First name: " + firstName;
    }
    
    public String getSurname(){
        return "Last name: " + surname;
    }
    
    public String getFullname(){
        return firstName + " " + surname;
    }
        
    public String getBornDate(){
        return "Born: " + born.toString();
    }
            
    public String getDiedDate(){
        return "Died: " + died.toString();
    }
                
    public String getBornCountry(){
        return "Born Country: " + bornCountry;
    }
    
    public String getBornCountryCode(){
        return "Born Country Code: " + bornCountryCode;
    }
    
    public String getBornCity(){
        return "Born City: " + bornCity;
    }
    
    public String getDiedCountry(){
        return "Died Country: " + diedCountry;
    }
    
    public String getDiedCountryCode(){
        return "Died Country Code: " + diedCountryCode;
    }
    
    public String getDiedCity(){
        return "Died City: " + diedCity;
    }
    
    public String getGender(){
        return "Gender: " + gender;
    }
    
    public String getJpegURL(){
        return jpegURL;
    }
    
    public String getAllMotivations(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < prizes.size(); i++) {
            s.append("Prize:\n\t");
            s.append("Category: ");
            s.append(prizes.get(i).category());
            s.append("\n\t");
            s.append("Year: ");
            s.append(prizes.get(i).getYear());
            s.append("\n\t");
            s.append("Motivation: ");
            s.append(prizes.get(i).getMotivation());
            s.append("\n\t");
            for (int j = 0; j < prizes.get(i).affiliations.size(); j++) {
                s.append("Affiliations:\n\t\t");
                s.append(prizes.get(i).affiliations.get(j).getAffiliations());
            }
            s.append("\n\tShare: ");
            s.append(prizes.get(i).getShare());
            s.append("\n");
        }
        return s.toString();
    }
}
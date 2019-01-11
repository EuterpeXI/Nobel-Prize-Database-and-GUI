/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pbord
 */
public class Database {

    ArrayList<Person> database = new ArrayList<>();

    Database(UndoManager undo) {
        try {
            database = personMaker(getData());
        } catch (Exception E) {
            System.out.println("There was an error building the database.");
            System.exit(0);
        }
        ArrayList<Person> copy = new ArrayList<>();
        copy = database;
        undo.add(copy);
        System.out.println("");
    }

//    private Database clone(Database d){
//        UndoManager throwAway = new UndoManager();
//        Database newD = new Database(throwAway);
//        newD = d;
//        return newD;
//        
//    }
    /*
    Parameters: Index position (int)
    Return: Person object stored at that index position within the database 
     */
    public Person getPerson(int i) {
        return database.get(i);
    }

    /*
    Return: count within database
     */
    public int getCount() {
        return database.size();
    }

    /*
    Parameters: String (single character) - used as search value to search through the database
    Return: List of Person objects corresponding to the parameter
     */
    public void searchSurname(String character, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            if (p.surname.startsWith(character)) {
                output.add(p);
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.surname.compareTo(p2.surname));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    /*
    Parameters: String (single character) - used as search value to search through the database
    Return: List of Person objects corresponding to the parameter
     */
    public void searchFirstname(String character, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            if (p.firstName.startsWith(character)) {
                output.add(p);
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.firstName.compareTo(p2.firstName));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    public Person getSinglePerson(String firstLast) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            if (p.getFullname().equals(firstLast)) {
                return p;
            }
        }
        return database.get(0); //will never get here
    }

    public void searchCountry(String country, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            if (p.bornCountry.contains(country)) {
                output.add(p);
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.surname.compareTo(p2.surname));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    /*
    Parameters: String (single character) - used as search value to search through the database
    Return: List of Person objects corresponding to the parameter
     */
    public void searchGender(String gender, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            if (p.gender.equals(gender)) {
                output.add(p);
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.surname.compareTo(p2.surname));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    public void searchPrize(String prize, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            for (Prize pz : p.prizes) {
                if (pz.category.equals(prize)) {
                    output.add(p);
                }
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.surname.compareTo(p2.surname));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    public void searchYears(double lower, double upper, UndoManager undo) {
        ArrayList<Person> output = new ArrayList<>();
        for (Person p : database) {
            for (Prize pz : p.prizes) {
                if (lower <= pz.year && upper >= pz.year) {
                    output.add(p);
                }
            }
        }
        //sort the output alphabetically
        Collections.sort(output, (Person p1, Person p2) -> p1.surname.compareTo(p2.surname));
        //change the database reference to the new database
        database = output;
        ArrayList<Person> copy = new ArrayList<Person>();
        copy = database;
        undo.add(copy);
    }

    private static String parseObject(JSONObject element, String field) {
        String output;
        try {
            output = element.getString(field);
        } catch (Exception e) {
            output = "No data";
        }
        return output;
    }

    private static int parseObjectInt(JSONObject element, String field) {
        int output;
        try {
            output = Integer.parseInt(element.getString(field));
        } catch (Exception e) {
            output = 0;
        }
        return output;
    }

    private static LocalDate parseObjectDate(JSONObject element, String field) {
        LocalDate output;
        try {
            output = LocalDate.parse(parseObject(element, "born"));
        } catch (Exception e) {
            output = LocalDate.MIN;
        }
        return output;
    }

    private static ArrayList<Person> personMaker(String s) {

        JSONObject obj = new JSONObject(s);
        JSONArray myList;
        myList = obj.getJSONArray("laureates");
        JSONObject element;

        LocalDate day;

        ArrayList<Person> personList = new ArrayList<>();

        for (int i = 0; i < myList.length(); i++) {
            element = myList.getJSONObject(i);
            Person p = new Person();
            p.id = Integer.parseInt(element.getString("id"));

            p.firstName = parseObject(element, "firstname");
            p.bornCountry = parseObject(element, "bornCountry");
            p.diedCountry = parseObject(element, "diedCountry");
            p.diedCity = parseObject(element, "diedCity");
            p.diedCountryCode = parseObject(element, "diedCountryCode");
            p.bornCity = parseObject(element, "bornCity");
            p.bornCountryCode = parseObject(element, "bornCountryCode");
            p.surname = parseObject(element, "surname");
            p.diedCity = parseObject(element, "diedCity");
            p.gender = element.getString("gender");

            JSONArray prizes = element.getJSONArray("prizes");
            ArrayList<Prize> prizeList = new ArrayList<>();

            for (int j = 0; j < prizes.length(); j++) {
                JSONObject prizeObj = prizes.getJSONObject(j);
                int prizeDay = parseObjectInt(prizeObj, "year");
                String category = parseObject(prizeObj, "category");
                int share = parseObjectInt(prizeObj, "share");
                String motivation = parseObject(prizeObj, "motivation");

                JSONArray aff = prizeObj.getJSONArray("affiliations");

                ArrayList<Affiliation> affList = new ArrayList<>();

                for (int x = 0; x < aff.length(); x++) {
                    try {
                        JSONObject affObj = aff.getJSONObject(x);
                        String name = parseObject(affObj, "name");
                        String city = parseObject(affObj, "city");
                        String country = parseObject(affObj, "country");
                        Affiliation a = new Affiliation(name, city, country);
                        affList.add(a);
                    } catch (Exception E) {
                        //This never throws an exception but it doesnt work if not in a try/catch.
                    }
                }

                Prize pz = new Prize(prizeDay, motivation, affList, share, category);
                prizeList.add(pz);
            }
            p.prizes = prizeList;
            p.born = parseObjectDate(element, "born");
            p.died = parseObjectDate(element, "died");
            p.jpegURL = p.setjpegURL();
            personList.add(p);

        }
        return personList;
    }

    private static String getData() throws MalformedURLException, IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://api.nobelprize.org/v1/laureate.json?");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception E) {

        }
        return result.toString();
    }

    public Set<String> getCountrySet() {
        List<String> output = new ArrayList<>();

        database.forEach((p) -> {
            output.add(p.bornCountry);//change to be off the selector parameter.
        });
        return new TreeSet(output);
    }

    public Set<Integer> getYearSet() {
        List<Integer> output = new ArrayList<>();

        database.forEach((person) -> {
            person.prizes.forEach((prize) -> {
                output.add(prize.year);
            });
        });
        return new TreeSet(output);
    }

    public Set<String> getPrizeSet() {
        List<String> output = new ArrayList<>();

        database.forEach((person) -> {
            person.prizes.forEach((aff) -> {
                output.add(aff.category);
            });
        });
        return new TreeSet(output);
    }

}

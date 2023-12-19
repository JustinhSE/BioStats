import java.io.*;
import java.util.*;

public class PersonDataManager {
    //Tripathi allowed me to declare this as public in office hours
    public static Person[] people;
    private static Person[] newPeople;
    private static String n, g;
    private static double w, h;
    private static int count, a;

    public static void buildFromFile(String location) throws IllegalArgumentException, PersonAlreadyExistsException {
        Person patient;
        String path, oldPath;
        //first scanner counting rows
        Scanner oldSc = new Scanner(System.in);
        //second scanner extracting info
        Scanner sc = new Scanner(System.in);
        try {
            //read file given
            oldPath = location;
            File file1 = new File(oldPath);
            oldSc = new Scanner(file1);
        }

        catch(FileNotFoundException exception){
            exception.printStackTrace();
        }

        //try catch file not found
        count  = 0;
        while (oldSc.hasNextLine()) {
            count++;
            //count number of rows
            String temp = oldSc.nextLine();
        }
        //used later
        boolean firstRow = true;
        //array length = number of rows - header
        people = new Person[count-1];

        try {
            //extract info from this file
            path = location;
            File file = new File(path);
            sc = new Scanner(file);
        }

        catch(FileNotFoundException exception){
            exception.printStackTrace();
        }

        while(sc.hasNextLine()) {
            if (firstRow) {
                //do not intake header
                firstRow = false;
                String trash = sc.nextLine();
            }

            else {
                //second pointer for comma
                int l2 = 0;
                //numbers array
                String[] checker = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                String item = sc.nextLine();
                item = item.replaceAll("\\s", "");
                //looking to see if integer found in the name string
                try {
                    l2 = item.indexOf(',');
                    //takes out the name from csv file
                    n = item.substring(0, l2);
                    for (int p = 0; p < checker.length; p++) {
                        if (n.contains(checker[p])) {
                            throw new IllegalArgumentException();
                        }
                    }

                }//if found then do not fully load file
                catch (IllegalArgumentException e) {
                    System.out.println("There was a number found within the name: " + n);
                    System.out.println("Person data did not load successfully. ");
                    break;

                }

                //extract values based on commas, using indexOf, and parsing ints/doubles
                try {
                    l2 += 4;
                    g = item.substring(l2, l2 + 1);
                    int l1 = l2 + 1;
                    l1 = (item.indexOf(',', l2)) + 1;
                    l2 = item.indexOf(',', l1 + 1);
                    a = Integer.parseInt(item.substring(l1, l2));
                    l1 = l2 + 1;
                    l1 = (item.indexOf(',', l2)) + 1;
                    l2 = item.indexOf(',', l1 + 1);
                    h = Double.parseDouble(item.substring(l1, l2));
                    l1 = l2 + 1;
                    l1 = (item.indexOf(',', l2)) + 1;
                    l2 = item.length();
                    w = Double.parseDouble(item.substring(l1, l2));
                }
                catch(InputMismatchException ex){
                    System.out.println("There may be something wrong with your code here..." + ex.getMessage());

                }
                finally {
                    patient = new Person();
                    patient.setName(n);
                    patient.setAge(a);
                    patient.setGender(g);
                    patient.setHeight(h);
                    patient.setWeight(w);
                    //add patient to the array
                    PersonDataManager.addPerson(patient);

                }
            }
        }
        //close scanners
        oldSc.close();
        sc.close();
        System.out.println("Person data loaded successfully!");
    }

    //compareTo method

    //sorting names alphabetically
    public static Person[] compareTo(Person[] array){
        for(int i = 0; i < array.length; i++){
            //stop running once hitting null value
            if(array[i] == null ){
                break;
            }
            //caleb(i) > adam(j) adam caleb
            for(int j = i+1; j < array.length; j++){
                //do not sort using null values
                if(array[j] == null ){
                    break;
                }
                if(array[i].getName().compareTo(array[j].getName()) > 0){
                    Person temp1 = array[j];
                    array[j] = array[i];
                    array[i] = temp1;
                }
            }
        }
        return array;
    }

    public static void addPerson(Person newPerson) throws PersonAlreadyExistsException {
        boolean newP = false;
        try {

            for (int i = 0; i < people.length; i++) {
                //skip anyone who is null
                if (people[i] == null) {
                    break;
                }
                //do not add new person
                if (people[i].getName().equals(newPerson.getName())) {
                    newP = false;
                    throw new PersonAlreadyExistsException(newPerson);
                }
            }
            //if array has reached its capacity, make the array bigger
        if (people[people.length - 1] != null) {
            newPeople = new Person[people.length * 5];
            System.arraycopy(people, 0, newPeople, 0, people.length);
            newPeople[people.length] = newPerson;
            people = newPeople;
            newP = true;

        } else {
            //add person if array is not too big and person is not found in array
            int look = 0;
            //find first spot to add person in array
            for (int j = 0; j < people.length; j++) {
                if (people[j] == null) {
                    look = j;
                    break;
                }

            }
            //add person to list
            people[look] = newPerson;
        }
        //sort person into list
        people = compareTo(people);
        }catch(PersonAlreadyExistsException ex){
            System.out.println("Sorry, this person is already in the database. ");
        }
        finally{
            //if new person added tell user they have been
            if(newP)
                System.out.println(newPerson.getName() + " has been added!");
        }
    }
    public void getPerson(String newName) throws PersonDoesNotExistsException {
        try {
            //do not continue searching if it is null
            for (int i = 0; i< people.length; i++) {
                if(people[i] == null){
                    break;
                }
                //stop searching if person is found and print the info
                if (people[i].getName().equals(newName)) {
                    System.out.println(people[i].toStringGP());
                    return;
                }
            }
            //thrown if person is not found
             throw new PersonDoesNotExistsException(newName);
        }
        catch(PersonDoesNotExistsException ex){
            System.out.println("Sorry, this person doesn't exist");
        }
    }

    public void removePerson(String newName) throws PersonDoesNotExistsException {
        boolean isFound = false;
        try {
            for (int i = 0; i< people.length; i++) {
                //stop searching if pelement is null
                if(people[i] == null){
                    break;
                }
                //remove person
                if (people[i].getName().equals(newName)) {
                    people[i] = null;
                    //shift values so person is not missing
                    PersonDataManager.shift(i, people);
                    isFound = true;
                    return;
                }

            }
            //if not found tell user they cannot be removed
            if(!isFound)
                throw new PersonDoesNotExistsException(newName);
        }
        catch(PersonDoesNotExistsException ex){
            System.out.println("This person was not found. Try again.");
        }
        finally{
            System.out.println(newName + " has been removed.");
        }

    }
    //shift elements so there are no holes in the array
    public static void shift(int index, Person[] p){
        for(int i = index +1 ; i < p.length; i++){
            if(p[i-1] != (p[i]) )
                p[i-1] = p[i];

        }
        //last element erase
        p[p.length-1] = null;
        people = p;
    }
    public void printTable()
    {
        //printing table header
        System.out.println("   Name   " + "|" + "   Age   " + "|" + " Gender " + "|" + "     Height     "  + "|" + "     Weight   ");
            for(int j = 0; j < 13*5; j++)
                System.out.print("=");
            System.out.println();
        //once the next element is null, stop the program
        for (int i = 0; i< people.length; i++) {
            if(people[i] == null){
                break;
            }
            //gather elements
            System.out.println(people[i].toString());
            }
        }



    }


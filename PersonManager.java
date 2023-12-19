import java.util.*;
import java.io.*;

public class PersonManager {
    public static PersonDataManager personDataManager;
    private static Person patient;

    public static void main(String[] args) throws PersonAlreadyExistsException, PersonDoesNotExistsException, IOException {
        personDataManager = new PersonDataManager();
        //new scanner
        Scanner input = new Scanner(System.in);
        //start of program
        System.out.println("Hey! Let's get started!");
        System.out.println("Starting..");
        String option = " ";
        //while user doesn't quit
        while (!(option.equals("q"))) {
            //menu
            System.out.println("Menu:");
            //asks what option
            System.out.println("(I) - Import from File");
            System.out.println("(A) - Add Person");
            System.out.println("(R) - Remove Person");
            System.out.println("(G) - Get Info on Person");
            System.out.println("(P) - Print Table");
            System.out.println("(S) - Save to File");
            System.out.println("(Q) - Quit");
            System.out.println("Please select an option: ");
            option = input.nextLine();

            //Lower case option
            option = option.toLowerCase();


            if (option.equals("i")) {
                //asks where
                System.out.println("Please enter a location: ");
                String looking = input.nextLine();
                System.out.println("Loading... ");
                PersonDataManager.buildFromFile(looking);
                //do not read rows that contain no info
                for (int counting = 0; counting < PersonDataManager.people.length; counting++) {
                    if (PersonDataManager.people[counting] == null) {

                    }
                }
            }
            //option a
            if (option.equals("a")) {

                patient = new Person();
                //adding new person if all info is in correct format
                try {
                    System.out.println("Please enter the name of the person: ");
                    patient.setName(input.nextLine());
                    System.out.println("Please enter the age: ");
                    patient.setAge(input.nextInt());
                    System.out.println("Please enter the height(in inches): ");
                    patient.setHeight(input.nextDouble());
                    System.out.println("Please enter the weight(in lbs): ");
                    patient.setWeight(input.nextDouble());
                    System.out.println("Please enter the gender (M or F): ");
                    String exempt = input.nextLine();
                    String temp2 = input.nextLine();
                    patient.setGender(temp2);
                } catch (InputMismatchException ex) {
                    System.out.println("The input you entered is incorrect, Please try again.");
                }
                PersonDataManager.addPerson(patient);


            }
            //option - r
            if (option.equals("r")) {
                //removes person
                System.out.println("Please enter the name of the person: ");
                String personRemoved = input.nextLine();
                personDataManager.removePerson(personRemoved);
            }
            //option - g
            if (option.equals("g")) {
                System.out.println("Please enter the name of the person: ");
                String personName = input.nextLine();
                personDataManager.getPerson(personName);


            }
            //option - p
            if (option.equals("p")) {
                personDataManager.printTable();
            }
            //option - s
            if (option.equals("s")) {
                System.out.println("Please enter the name for the file: (___.csv) ");
                String file = input.nextLine();
                //create new file with saved info
                File f = new File(file);
                try {
                    //initialized file & print writers
                    FileWriter fw = new FileWriter(f, true);
                    PrintWriter pw = new PrintWriter(fw);
                    //header of csv file
                    pw.println("Name,\"     \"\"Sex\"\"\",\" \"\"Age\"\"\",\" \"\"Height (in)\"\"\",\" \"\"Weight (lbs)\"\"\"");
                    //send to file
                    pw.flush();
                    //edit this print statement
                    //once at the end of the array stop reading
                    for (int flushing = 0; flushing < PersonDataManager.people.length; flushing++) {
                        if (PersonDataManager.people[flushing] == null) {
                            break;
                        }
                        pw.println(PersonDataManager.people[flushing].getName() + ",\"       \"\"" + PersonDataManager.people[flushing].getGender() + "\"\"\"," + PersonDataManager.people[flushing].getAge() + "," + (int) PersonDataManager.people[flushing].getH() + "," + (int) PersonDataManager.people[flushing].getW());

                        pw.flush();
                    }

                } catch (IOException ex) {
                    System.out.println("Not correct file name -> " + ex.getMessage());

                } finally {
                    System.out.println("A file named " + file + " has been created!");

                }


            }
            //quit
            if (option.equals("q")) {
                input.close();
                System.out.println("Sorry to see you go!");
            }
        }
    }
}
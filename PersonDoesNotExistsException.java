public class PersonDoesNotExistsException extends Exception {
    private Person p;
    //if person does not exist print that they are not found
    public PersonDoesNotExistsException(String p){
        super("Person Not Found: " + p);
    }
}

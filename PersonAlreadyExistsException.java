public class PersonAlreadyExistsException extends Exception {
    private Person ex;
    //if person exists say they were found
    public PersonAlreadyExistsException(Person human){
        super("Person Already Exists Found: " + human.getName());
    }
}

public class Person{
    private int age;
    private double height, weight;
    private  String name, gender;
    //creates a new person
    public Person(){
        age = 0;
        height = 0.0;
        weight = 0.0;
        name = null;
        gender = null;
    }
    //creates a new person with values
    public Person(int age, double height, double weight, String name, String gender){
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.gender = gender;

    }

    public double getH(){
       return height;
    }
    public double getW(){

        return weight;
    }
    //get weight w/ pounds for table
    public String getWeight(){
        //adjusting spacing within table toString()
        String extra = "";
        if(weight < 100){
            extra = " ";
        }
        if(weight < 10){
            extra = "  ";
        }

        return extra + (int)weight + " pounds";
    }
    public String getGender(){
        return gender;
    }
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
    //converting M or F to male or female
    public String getGenderTS(){

        if(gender.toLowerCase().equals("m"))
                return "male";
        if(gender.toLowerCase().equals("f")){
            return "female";
        }
        else{
            return "";
        }
    }
   //tabular height and modifying whitespace within column
    String space = "";
    public String getHeight(){
        int feet = (int) height/12;
        int inch = (int)(Math.round(((height/12) - feet) * 12));
        if(inch < 9.9){
            space = " ";
        }
        return feet + " feet " + inch + " inches" + space;
    }
    //toString height
    public String getHeightTS(){
        int feet = (int) height/12;
        int inch = (int)(Math.round(((height/12) - feet) * 12));
        if(inch < 9.9){
            space = " ";
        }
        return feet + " feet and " + inch + " inches" ;
    }
    public void setName(String p){
        name = p;
    }
    public void setAge(int a){
        this.age = a;
    }

    public void setGender(String g) {
        this.gender = g;
    }

    public void setHeight(double height) {
        this.height = height;

    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    //tabular toString
    public String toString(){
        return("   " + getName()+ "   " + "|" + "   "+ getAge() + "    " + "|" + "   "+ getGender() + "   " + " |" +  getHeight()  + "|" + "    " + getWeight() +"  ");

    }
    //toString for getting info on a person
    public String toStringGP(){
        return name + " is a " + age + " year old " + getGenderTS() + " who is " + getHeightTS() + " tall and weighs " + (int)weight + " pounds.";
    }


}
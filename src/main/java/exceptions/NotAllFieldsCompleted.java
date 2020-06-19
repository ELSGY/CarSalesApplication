package exceptions;

public class NotAllFieldsCompleted extends RuntimeException{
    public String toString(){
        return "you don not complete all fields";
    }
}

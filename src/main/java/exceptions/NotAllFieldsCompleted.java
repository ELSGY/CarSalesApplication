package exceptions;

public class NotAllFieldsCompleted extends RuntimeException{
    public String toString(){
        return "Not all fields are completed";
    }
}

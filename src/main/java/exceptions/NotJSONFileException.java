package exceptions;

public class NotJSONFileException extends RuntimeException{
    public String toString(){
        return "Not JSON File";
    }
}

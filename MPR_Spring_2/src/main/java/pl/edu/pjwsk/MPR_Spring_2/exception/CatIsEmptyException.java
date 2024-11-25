package pl.edu.pjwsk.MPR_Spring_2.exception;

public class CatIsEmptyException extends RuntimeException{
    public CatIsEmptyException(){
        super("Field is empty!!");
    }
}
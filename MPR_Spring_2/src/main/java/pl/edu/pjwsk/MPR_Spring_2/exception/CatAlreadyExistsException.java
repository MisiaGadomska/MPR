package pl.edu.pjwsk.MPR_Spring_2.exception;

public class CatAlreadyExistsException extends RuntimeException{
    public CatAlreadyExistsException(){
        super("Cat already exists!");
    }
}

package pl.edu.pjwsk.MPR_Spring_2.exception;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(){
        super("Cat Not Found!!");
    }
}

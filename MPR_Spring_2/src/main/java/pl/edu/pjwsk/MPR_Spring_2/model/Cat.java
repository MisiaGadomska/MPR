package pl.edu.pjwsk.MPR_Spring_2.model;

public class Cat {
    private String name;
    private String color;

    public Cat(String name, String color){
        this.name = name;
        this.color = color;
    }

    public Cat(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

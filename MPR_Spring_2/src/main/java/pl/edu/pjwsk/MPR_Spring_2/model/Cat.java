package pl.edu.pjwsk.MPR_Spring_2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String color;
    private Long identificator;


    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
        this.identificator = calculateIdentifier();
    }

    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public void setIdentificator(Long identificator) {
        this.identificator = identificator;
    }

    public Long getIdentificator() {
        return identificator;
    }

    public Long calculateIdentifier() {     //Identyfikator
        long suma = 0;
        // Obliczanie sumy wartości znaków z pola name
        if (this.name != null) {
            for (char c : this.name.toCharArray()) {
                suma += c;
            }
        }
        // Obliczanie sumy wartości znaków z pola color
        if (this.color != null) {
            for (char c : this.color.toCharArray()) {
                suma += c;
            }
        }
        return suma;        //Return sumy wartości znaków z pól name i color}
    }
}
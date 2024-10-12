package pl.edu.pjwsk.MPR_Spring_2.services;


import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatService {
    List<Cat> catList = new ArrayList<>();

    public CatService(){
       this.catList.add(new Cat("Meszek", "Brown"));
       this.catList.add(new Cat("Buba", "Black"));
       this.catList.add(new Cat("Ivan", "White"));
    }

    public List<Cat> getCatList() {
        return this.catList;
    }
    public void add(Cat cat) {
        this.catList.add(cat);
    }
    public Cat getCat(Integer id){
        return this.catList.get(id);
    }

    public void deleteCat(int id) {
        if (id >= 0 && id < this.catList.size()){
            this.catList.remove(id);
        } else {
            throw new IndexOutOfBoundsException("Nieprawidłowe id: " + id);
        }
    }

    public void updateCat(int id, Cat cat) {
        if (id >= 0 && id < this.catList.size()) {
            Cat existingCat = this.catList.get(id);
            existingCat.setName(cat.getName());
            existingCat.setColor(cat.getColor());
        } else {
            throw new IndexOutOfBoundsException("Nieprawidłowe id: " + id);
        }
    }
}

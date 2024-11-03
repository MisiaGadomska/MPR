package pl.edu.pjwsk.MPR_Spring_2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import pl.edu.pjwsk.MPR_Spring_2.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CatService {
    private StringUtilsService stringUtilsService;
    private List<Cat> catList = new ArrayList<>();
    private CatRepository repository;
    @Autowired
    public CatService(CatRepository repository){
        this.repository = repository;

       repository.save(new Cat("Meszek", "Brown"));
       repository.save(new Cat("Buba", "Black"));
       repository.save(new Cat("Ivan", "White"));
    }

    public List<Cat> getByName(String name) {
        return this.repository.findByName(name);
    }
    public List<Cat> getByColor(String color) {
        return this.repository.findByColor(color);
    }
    public List<Cat> getCatList() {
        return (List<Cat>) this.repository.findAll();
    }
    public void add(Cat cat) {
        this.repository.save(cat);
    }
    public Cat getCat(Long id) {
        Optional<Cat> catOptional = this.repository.findById(id);
        if (catOptional.isPresent()) {
        return catOptional.get();
        } else {
            return null;
        }
    }

    public void deleteCat(Long id) {
        if (repository.existsById(Long.valueOf(id))) {
            repository.deleteById(Long.valueOf(id));
        } else {
            throw new IndexOutOfBoundsException("Nieprawidłowe id: " + id);
        }
    }

    public void updateCat(Long id, Cat cat) {
        if (repository.existsById(id)){
            Cat existingCat = repository.findById(id).get();
            existingCat.setName(cat.getName());
            existingCat.setColor(cat.getColor());
            existingCat.setId(existingCat.getId());
            repository.save(existingCat);
        } else {
            throw new IndexOutOfBoundsException("Nieprawidłowe id: " + id);
        }
    }


}

package pl.edu.pjwsk.MPR_Spring_2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwsk.MPR_Spring_2.model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    public List<Cat> findByName(String name);
    public List<Cat> findByColor(String color);


}

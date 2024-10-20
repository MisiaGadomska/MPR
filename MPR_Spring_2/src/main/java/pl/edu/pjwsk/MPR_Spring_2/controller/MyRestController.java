package pl.edu.pjwsk.MPR_Spring_2.controller;

import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwsk.MPR_Spring_2.service.CatService;

import java.util.List;

@RestController
@RequestMapping("cat")
public class MyRestController {

    private CatService catService;

    @Autowired
    public MyRestController(CatService catService){
        this.catService = catService;
    }

    // @RequestMapping(method = RequestMethod.GET, value = "cat")
    @GetMapping("all") // endpoint
    public List<Cat> getAll(){
        return this.catService.getCatList();
    }

    @GetMapping("name/{name}")
        public List<Cat> findByName(@PathVariable String name){
            return this.catService.getByName(name);
        }

    @GetMapping("color/{color}")
        public  List<Cat> findByColor(@PathVariable String color){
        return this.catService.getByColor(color);
    }


    @GetMapping("{id}") //endpoint
    public Cat getCat(@PathVariable Long id){
        return this.catService.getCat(id);
    }

    @PostMapping("add")
    public void addCat(@RequestBody Cat cat){
        this.catService.add(cat);
    }


    @DeleteMapping("{id}")
    public void deleteCat(@PathVariable Long id){
        catService.deleteCat(id);
    }

    @PutMapping("{id}")
    public void updateCat(@PathVariable Long id, @RequestBody Cat cat){
        catService.updateCat(id, cat);
    }
}

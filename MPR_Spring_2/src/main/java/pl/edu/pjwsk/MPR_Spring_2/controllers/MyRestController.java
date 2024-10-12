package pl.edu.pjwsk.MPR_Spring_2.controllers;

import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwsk.MPR_Spring_2.services.CatService;

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

    @GetMapping("{id}") //endpoint
    public Cat getCat(@PathVariable int id){
        return this.catService.getCat(id);
    }

    @PostMapping("add")
    public void addCat(@RequestBody Cat cat){
        this.catService.add(cat);
    }


    @DeleteMapping("{id}")
    public void deleteCat(@PathVariable int id){
        catService.deleteCat(id);
    }

    @PutMapping("{id}")
    public void updateCat(@PathVariable int id, @RequestBody Cat cat){
        catService.updateCat(id, cat);
    }
}

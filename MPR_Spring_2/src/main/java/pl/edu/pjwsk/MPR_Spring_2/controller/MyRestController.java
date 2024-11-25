package pl.edu.pjwsk.MPR_Spring_2.controller;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pl.edu.pjwsk.MPR_Spring_2.exception.CatNotFoundException;
import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwsk.MPR_Spring_2.service.CatService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
    public ResponseEntity<List<Cat>> getAll(){
        return new ResponseEntity<>(catService.getCatList(), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
        public ResponseEntity<List<Cat>> findByName(@PathVariable String name){
            return new ResponseEntity<>(catService.getByName(name), HttpStatus.OK);
        }

    @GetMapping("color/{color}")
        public  ResponseEntity<List<Cat>> findByColor(@PathVariable String color){
            List<Cat> cat = catService.getByColor(color);
            if (cat.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        return new ResponseEntity<>(catService.getByColor(color), HttpStatus.OK);
    }

    @GetMapping("{id}") //endpoint
    public ResponseEntity<Cat> getCat(@PathVariable Long id){
        return new ResponseEntity<>(catService.getCatById(id), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        this.catService.add(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cat> deleteCat(@PathVariable Long id){
        catService.deleteCat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable Long id, @RequestBody Cat cat){
        catService.updateCat(id, cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}/pdf")
    public ResponseEntity<byte[]> getCatPdf(@PathVariable Long id) {
        Cat cat = catService.getCatById(id);
        if (cat == null) {
            throw new CatNotFoundException();
        }

        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 700);

                contentStream.showText("Cat details:");
                contentStream.newLine();
                contentStream.showText("ID: " + cat.getId());
                contentStream.newLine();
                contentStream.showText("Name: " + cat.getName());
                contentStream.newLine();
                contentStream.showText("Color: " + cat.getColor());
                contentStream.newLine();
                contentStream.endText();
            }

            document.save(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cat_" + id + ".pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

}

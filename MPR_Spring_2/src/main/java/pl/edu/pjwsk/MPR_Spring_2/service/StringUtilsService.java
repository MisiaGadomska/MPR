package pl.edu.pjwsk.MPR_Spring_2.service;

import org.springframework.stereotype.Service;

@Service
public class StringUtilsService {
    private CatService catService;
    public StringUtilsService(CatService catService) {
        this.catService = catService;
    }
    public String upper(String input){
        if (input != null){
            return input.toUpperCase();
        }
            return null;
    }
    public String lower(String input){
        if(input == null || input.isEmpty()){
            return null;
        }
        String lowerCase = input.toLowerCase();
        return Character.toUpperCase(lowerCase.charAt(0)) + lowerCase.substring(1);
    }
}

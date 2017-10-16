package com.marfeel.juniortest.controller;

import com.marfeel.juniortest.database.DataBase;
import com.marfeel.juniortest.dto.CityDto;
import com.marfeel.juniortest.service.PostalCodeService;
import com.marfeel.juniortest.service.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CitiesController {
    private final PostalCodeService postalCodeService;
    private DataBase DB;
    private final Searcher searcher;

    @Autowired
    public CitiesController(PostalCodeService postalCodeService, DataBase db, Searcher searcher) {
        this.postalCodeService = postalCodeService;
        this.DB = db;
        this.searcher = searcher;
    }

    /**
     * This request mapping is provided as an example
     *
     * e.g. http://localhost:8080/postalcodes/washington
     */
    @RequestMapping(value = "/postalcodes/{cityName}", method = RequestMethod.GET)
    public ResponseEntity<String> getPostalCode(@PathVariable String cityName) {
        return ResponseEntity.ok(postalCodeService.getPostalCode(cityName));
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST, consumes = "application/json")
    public void processCities(@RequestBody List<CityDto> cityDtos) {
        //TODO process cities
        List<String> citydone = new ArrayList<String>();
        List<String> statedone = new ArrayList<String>();
        List<CityDto> csdone = new ArrayList<CityDto>();

        for (CityDto city: cityDtos) {
            if(!citydone.contains(city.getCity())){
                citydone.add(city.getCity());
                DB.storeCI(city.getCity(),searcher.readCity(city.getCity()));
            }
            if(!statedone.contains(city.getState())){
                statedone.add(city.getState());
                DB.storeSI(city.getState(),searcher.readState(city.getState()));
            }
            if(!csdone.contains(city)){
                csdone.add(city);
                DB.storeCity(city.getCity(), city.getState());
            }
        }
    }

    @RequestMapping(value = "/staterequest/{stateName}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getCities(@PathVariable String stateName){
        return ResponseEntity.ok(DB.getCities(stateName));
    }

    @RequestMapping(value = "/statepopulation/{stateName}", method = RequestMethod.GET)
    public ResponseEntity<Double> getPopulation(@PathVariable String stateName){
        double res = 0.0;
        List<String> cities = DB.getCities(stateName);
        int aux = 0;
        for(String cityName: cities){
            res = DB.getCI(cityName).getPopulation();
            ++aux;
        }
        return new ResponseEntity<>(res/aux, HttpStatus.ACCEPTED);
    }
}

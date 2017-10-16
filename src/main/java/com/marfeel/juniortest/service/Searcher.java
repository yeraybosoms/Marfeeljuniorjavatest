package com.marfeel.juniortest.service;

import com.marfeel.juniortest.dto.CityInfo;
import com.marfeel.juniortest.dto.StateInfo;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import static java.lang.Math.toIntExact;


@Service
public class Searcher {
    private JSONParser parser = new JSONParser();
    private final List<String> cpath = Arrays.asList("/src/main/resources/properties/us_national_government_cities.json", "/src/main/resources/properties/worldwide_api_cities.json");
    private final List<String> spath = Arrays.asList("/src/main/resources/properties/states_data_api.json");
    public CityInfo readCity(String cityName){
        String filepath = new File("").getAbsolutePath();
        for (String path: cpath){
            JSONArray a = null;
            try {
                a = (JSONArray) parser.parse(new FileReader(filepath + path));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (Object o : a) {
                JSONObject city = (JSONObject) o;
                String aux;
                if (city.containsKey("name")) {
                    aux = city.get("name").toString();
                } else {
                    aux = city.get("cityName").toString();
                }

                if (aux.equals(cityName)) {
                    long settled;
                    if (city.containsKey("settled")) {
                        settled = (long) city.get("settled");
                    } else {
                        settled = (long) city.get("foundation");
                    }
                    return new CityInfo(city.get("mayor").toString(), toIntExact(settled), (long) city.get("population"));
                }
            }
        }return new CityInfo();//should never happens assuming there are info for all the cities in our files
    }

    public StateInfo readState(String stateName){
        String filepath = new File("").getAbsolutePath();
        for (String path: spath){
            JSONArray a = null;
            try {
                a = (JSONArray) parser.parse(new FileReader(filepath +path));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (Object o : a) {
                JSONObject state = (JSONObject) o;
                if (state.get("name").toString().equals(stateName)) {
                    return new StateInfo(state.get("timezone").toString(), state.get("governor").toString(), state.get("abbreviation").toString());
                }
            }
        }return new StateInfo();//should never happens assuming there are info for all the states in our files
    }
}

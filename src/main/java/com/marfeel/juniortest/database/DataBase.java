package com.marfeel.juniortest.database;

import com.marfeel.juniortest.dto.CityInfo;
import com.marfeel.juniortest.dto.StateInfo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataBase {
    private Map<String, CityInfo> cities = new HashMap<>();
    private Map<String, StateInfo> states = new HashMap<>();
    private Map<String, List<String>> statecity = new HashMap<>();

    public void storeCI(String cityName, CityInfo info){
        cities.put(cityName, info);
    }

    public void storeSI(String stateName, StateInfo info){
        states.put(stateName, info);
    }

    public void storeCity(String cityName, String stateName){
        if(!containsCS(cityName,stateName)) {
            if (statecity.containsKey(stateName)) {
                List<String> aux = statecity.get(stateName);
                aux.add(cityName);
                statecity.put(stateName, aux);
            } else {
                List<String> aux = new ArrayList<String>();
                aux.add(cityName);
                statecity.put(stateName, aux);
            }
        }
    }

    private boolean containsCS(String cityName, String stateName){
        if(statecity.containsKey(stateName)){
            return statecity.get(stateName).contains(cityName);
        }return false;
    }

    public List<String> getCities(String stateName){return statecity.get(stateName);}

    public CityInfo getCI(String cityName){return cities.get(cityName);}

    public StateInfo getSI(String stateName){return states.get(stateName);}

}

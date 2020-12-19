package com.tracker.coronavirustracker;

import com.tracker.coronavirustracker.model.LocationStates;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class coronavirusService {
    @Autowired
    RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    public List<LocationStates> getAllStates() {
        return allStates;
    }

    private List<LocationStates> allStates = new ArrayList<>();
    private static String CORONA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void loadFetchData() throws Exception {
        List<LocationStates> newStateList = new ArrayList<>();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseOfAccount = restTemplate.exchange(CORONA_URL, HttpMethod.GET, entity, String.class);
        StringReader stringReader = new StringReader(responseOfAccount.getBody());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            LocationStates locationStates = new LocationStates();
            locationStates.setState(record.get("Province/State"));
            locationStates.setCountry(record.get("Country/Region"));
            locationStates.setTotalCases(Integer.parseInt(record.get(record.size()-1))); //last colum no data print karse //
            System.out.println(locationStates);
            newStateList.add(locationStates);
        }
        this.allStates = newStateList;
    }
}

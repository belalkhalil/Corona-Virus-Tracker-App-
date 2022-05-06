package com.example.cotonavirustracker.Services;

import com.example.cotonavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/*
* typically we would make a Service stateless
* */
@Service
public class CronoaVirusDataService {

    // read csv file
    private String Virus_Data_URL= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats= new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    // excute method after creating instance of class
    //@Scheduled schedule the run of a method at a regular basis. Update method every 59 min
    @PostConstruct
    @Scheduled(cron = "* 59 * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        /*
        creating a new list for constructing new records
        if user hit while constructing he still get old records
        * */
        // make an HTTP Request
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request= HttpRequest.newBuilder().uri(URI.create(Virus_Data_URL)).build();

      //send request to client. take body and return it as a String
     HttpResponse<String> httpResponse=   client.send(request, HttpResponse.BodyHandlers.ofString());

        // add to persistent list
        allStats = parseCSV(httpResponse);
    }

    //parse String and read it from a file using commons-csv Liberary
    private List<LocationStats> parseCSV(HttpResponse<String> httpResponse) throws IOException {
        StringReader csvBodyReader = new StringReader((httpResponse.body()));
        List<LocationStats> newStats= new ArrayList<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            int thisDayTotalCases = Integer.parseInt(record.get(record.size() -1));
            int difFromPrevDay = Integer.parseInt(record.get(record.size() -1)) - Integer.parseInt(record.get(record.size() -2));
            //get column by header name
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountrey(record.get("Country/Region"));
            locationStat.setLastestTotalcases(thisDayTotalCases);
            locationStat.setDifFromPrevDay(difFromPrevDay);

            // add to temp list
            newStats.add(locationStat);

        }
        return newStats;
    }


}

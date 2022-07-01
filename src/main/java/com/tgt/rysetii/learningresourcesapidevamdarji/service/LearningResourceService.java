package com.tgt.rysetii.learningresourcesapidevamdarji.service;

import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResourceStatus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LearningResourceService {

    private ArrayList<LearningResource> loadLearningResourceFromCSV(File csv){
        ArrayList<LearningResource> learningResources = new ArrayList<>();
        try {
            FileReader reader = new FileReader(csv);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            line = bufferedReader.readLine();

            while(line!=null){
                String[] attributes = line.split(",");
                LearningResource learningResource = createLearningResource(attributes);
                learningResources.add(learningResource);
                line = bufferedReader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return learningResources;
    }

    private LearningResource createLearningResource(String[] attributes) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Integer id = Integer.parseInt(attributes[0]);
        String productName = attributes[1];
        Double costPrice = Double.parseDouble(attributes[2]);
        Double sellingPrice = Double.parseDouble(attributes[3]);
        LearningResourceStatus status = LearningResourceStatus.valueOf(attributes[4]);
        LocalDate createdDate = LocalDate.parse(attributes[5], dateFormatter);
        LocalDate publishedDate = LocalDate.parse(attributes[6], dateFormatter);
        LocalDate retiredDate = LocalDate.parse(attributes[7], dateFormatter);
        LearningResource learningResource =
                new LearningResource(
                        id,productName,costPrice,sellingPrice,status,createdDate,publishedDate,retiredDate
                );
        return learningResource;
    }

}

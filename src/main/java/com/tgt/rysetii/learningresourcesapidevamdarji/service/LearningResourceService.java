package com.tgt.rysetii.learningresourcesapidevamdarji.service;

import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResourceStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
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

    private void saveLearningResourcesToCSV(ArrayList<LearningResource> learningResources){
        String seperator = ",";

        try{
            File learningResourceCSV = new File("LearningResource.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(learningResourceCSV.getName(),true));
            for(LearningResource learningResource: learningResources){
                writer.newLine();
                StringBuffer line = new StringBuffer();
                line.append(learningResource.getLearningResourceID());
                line.append(seperator);
                line.append(learningResource.getResourceName());
                line.append(seperator);
                line.append(learningResource.getCostPrice());
                line.append(seperator);
                line.append(learningResource.getSellingPrice());
                line.append(seperator);
                line.append(learningResource.getLearningResourceStatus());
                line.append(seperator);
                line.append(learningResource.getCreatedDate());
                line.append(seperator);
                line.append(learningResource.getPublishedDate());
                line.append(seperator);
                line.append(learningResource.getRetiredDate());
                writer.write(line.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<LearningResource> getLearningResources(){
        File learningResourceCSV = new File("LearningResource.csv");
        ArrayList<LearningResource> learningResources = loadLearningResourceFromCSV(learningResourceCSV);
        return learningResources;
    }

    private ArrayList<Double> getProfitMargin(){
        ArrayList<LearningResource> learningResources = getLearningResources();
        ArrayList<Double> profitMargins = new ArrayList<>();
        for(LearningResource learningResource:learningResources){
            Double margin = (learningResource.getSellingPrice() - learningResource.getCostPrice())/learningResource.getSellingPrice();
            profitMargins.add(margin);
        }
        return profitMargins;
    }

    private ArrayList<LearningResource> sortLearningResourcesByProfitMargins(){
        ArrayList<LearningResource> learningResources = getLearningResources();

        learningResources.sort((ele1,ele2) -> {
            Double margin1 = (ele1.getSellingPrice() - ele1.getCostPrice())/ele1.getSellingPrice();
            Double margin2 = (ele2.getSellingPrice() - ele2.getCostPrice())/ele2.getSellingPrice();

            return margin2.compareTo(margin1);
        });
        return learningResources;
    }

}

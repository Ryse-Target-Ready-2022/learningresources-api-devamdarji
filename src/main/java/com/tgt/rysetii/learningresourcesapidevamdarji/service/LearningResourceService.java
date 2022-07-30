package com.tgt.rysetii.learningresourcesapidevamdarji.service;

import com.tgt.rysetii.learningresourcesapidevamdarji.Repository.LearningResourceRepository;
import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResourceStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LearningResourceService {

    private final LearningResourceRepository learningResourceRepository;

    public LearningResourceService(LearningResourceRepository learningResourceRepository){
        this.learningResourceRepository = learningResourceRepository;
    }

    public void saveLearningResources(List<LearningResource> learningResources){
        for (LearningResource learningResource : learningResources)
            learningResourceRepository.save(learningResource);
    }

    public List<LearningResource> getLearningResources(){
        return learningResourceRepository.findAll();
    }

    private List<Double> getProfitMargin(){
        List<LearningResource> learningResources = getLearningResources();
        List<Double> profitMargins = new ArrayList<>();
        for(LearningResource learningResource:learningResources){
            Double margin = (learningResource.getSellingPrice() - learningResource.getCostPrice())/learningResource.getSellingPrice();
            profitMargins.add(margin);
        }
        return profitMargins;
    }

    private List<LearningResource> sortLearningResourcesByProfitMargins(){
        List<LearningResource> learningResources = getLearningResources();

        learningResources.sort((ele1,ele2) -> {
            Double margin1 = (ele1.getSellingPrice() - ele1.getCostPrice())/ele1.getSellingPrice();
            Double margin2 = (ele2.getSellingPrice() - ele2.getCostPrice())/ele2.getSellingPrice();

            return margin2.compareTo(margin1);
        });
        return learningResources;
    }

    public void deleteLearningResourceById(int learningResourceId){
        learningResourceRepository.deleteById(learningResourceId);
    }
}

package com.tgt.rysetii.learningresourcesapidevamdarji.Controllers;

import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapidevamdarji.service.LearningResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learningresources/v1")
public class LearningResourceController {
    private final LearningResourceService learningResourceService;

    public LearningResourceController(LearningResourceService learningResourceService){
        this.learningResourceService = learningResourceService;
    }

    @GetMapping("/")
    public List<LearningResource> getAllLearningResource(){
        return learningResourceService.getLearningResources();
    }

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLearningResources(@RequestBody List<LearningResource> learningResources){
        learningResourceService.saveLearningResources(learningResources);
    }

    @DeleteMapping(value = "/learningresource/{learningResourceId}")
    public void deleteLearningResource(@PathVariable int learningResourceId){
        learningResourceService.deleteLearningResourceById(learningResourceId);
    }
}

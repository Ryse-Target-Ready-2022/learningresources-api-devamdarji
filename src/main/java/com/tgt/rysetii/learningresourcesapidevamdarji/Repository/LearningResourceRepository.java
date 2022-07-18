package com.tgt.rysetii.learningresourcesapidevamdarji.Repository;

import com.tgt.rysetii.learningresourcesapidevamdarji.entity.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository<LearningResource,Integer> {
}

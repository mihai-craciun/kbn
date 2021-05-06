package com.mihaicraciun.kbn.kbn.services;

import java.util.List;
import java.util.stream.Collectors;

import com.mihaicraciun.kbn.kbn.model.Project;
import com.mihaicraciun.kbn.kbn.model.User;
import com.mihaicraciun.kbn.kbn.repositories.WorksOnRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorksOnServiceImpl implements WorksOnService{

    @Autowired
    WorksOnRepository worksOnRepository;

    @Override
    public List<Project> getProjectsByUser(User user) {
        return worksOnRepository
        .findByUser(user).stream()
        .map(w -> w.getProject())
        .collect(Collectors.toList());
    }
    
}

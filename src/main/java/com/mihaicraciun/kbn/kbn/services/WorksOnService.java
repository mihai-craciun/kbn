package com.mihaicraciun.kbn.kbn.services;

import java.util.List;

import com.mihaicraciun.kbn.kbn.model.Project;
import com.mihaicraciun.kbn.kbn.model.User;

public interface WorksOnService {
    List<Project> getProjectsByUser(User user);
}

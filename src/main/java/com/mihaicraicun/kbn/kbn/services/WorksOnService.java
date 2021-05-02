package com.mihaicraicun.kbn.kbn.services;

import java.util.List;

import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.User;

public interface WorksOnService {
    List<Project> getProjectsByUser(User user);
}

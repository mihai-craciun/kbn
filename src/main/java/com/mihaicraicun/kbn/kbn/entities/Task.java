package com.mihaicraicun.kbn.kbn.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    private String name;

    @Column(nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    private PriorityType priorityType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private User asignee;

    private Integer storyPoints = 0;

    /**
     * The type of task
     */
    enum TaskType {
        FEATURE, BUG;
    }

    /**
     * The priority
     */
    public enum PriorityType {
        HIGHEST_PRIORITY, HIGH_PRIORITY, MEDIUM_PRIORITY, LOW_PRIORITY, LOWEST_PRIORITY;
    }
}

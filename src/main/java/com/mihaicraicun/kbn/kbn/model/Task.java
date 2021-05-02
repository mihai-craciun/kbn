package com.mihaicraicun.kbn.kbn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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

    @Enumerated(EnumType.STRING)
    private State taskState;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User asignee;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    
    private Integer storyPoints = 0;

    private Date created;
    
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
    
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

    /**
     * The current task state
     */
    public enum State {
        BACKLOG, TO_DO, IN_PROGRESS, IN_REVIEW, IN_TEST, CLOSED;
    }
}

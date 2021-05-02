package com.mihaicraicun.kbn.kbn.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Column(name = "description", nullable = true)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "priority_type")
    private PriorityType priorityType;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "task_state")
    private State taskState;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User asignee;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    
    @Column(name = "story_points")
    private Integer storyPoints = 0;

    @Column(name = "created_at")
    private Date created;
    
    @Column(name = "updated_at")
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        this.setId(UUID.randomUUID().toString());
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

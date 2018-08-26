package com.thoughtworks.training.xueqing.todoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Where(clause = "deleted = false")
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Boolean deleted;
    private Boolean completed;
    private Date time;
    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="todo_id")
    private List<Task> tasks;
    @Transient
    private String suggestion;
}

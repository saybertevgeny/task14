package ru.lanit.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notebook")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String theme;

    private String text;

    @Enumerated(value = EnumType.ORDINAL)
    private PriorityEnum priority;

    private LocalDate deadline;

    public int getId() {
        return id;
    }

    public Notebook setId(int id) {
        this.id = id;
        return this;
    }

    public String getTheme() {
        return theme;
    }

    public Notebook setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public String getText() {
        return text;
    }

    public Notebook setText(String text) {
        this.text = text;
        return this;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public Notebook setPriority(PriorityEnum priority) {
        this.priority = priority;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Notebook setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }
}

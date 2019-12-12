package ru.lanit.dto;

public class NotebookResponse {

    private int id;

    private String theme;

    private String text;

    private String deadline;

    private String priority;

    public NotebookResponse(int id, String theme, String text, String deadline, String priority) {
        this.id = id;
        this.theme = theme;
        this.text = text;
        this.deadline = deadline;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public NotebookResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getTheme() {
        return theme;
    }

    public NotebookResponse setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public String getText() {
        return text;
    }

    public NotebookResponse setText(String text) {
        this.text = text;
        return this;
    }

    public String getDeadline() {
        return deadline;
    }

    public NotebookResponse setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public NotebookResponse setPriority(String priority) {
        this.priority = priority;
        return this;
    }
}

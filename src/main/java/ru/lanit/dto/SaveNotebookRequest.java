package ru.lanit.dto;

import ru.lanit.constraint.StringEnumConstraint;
import ru.lanit.entity.PriorityEnum;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SaveNotebookRequest {

    private int id;

    @NotBlank(message = "Тема обязательна")
    private String theme;

    private String text = "";

    @NotBlank(message = "Необходимо указать приоритет")
    @StringEnumConstraint(enumeration = PriorityEnum.class, message = "Не существующий приоритет")
    private String priority;

    @NotNull(message = "Дата дедлайна должны быть заполнена")
    @Future(message = "Дата дедлайна должна быть в будущем")
    private LocalDate deadline;

    public int getId() {
        return id;
    }

    public SaveNotebookRequest setId(int id) {
        this.id = id;
        return this;
    }

    public String getTheme() {
        return theme;
    }

    public SaveNotebookRequest setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public String getText() {
        return text;
    }

    public SaveNotebookRequest setText(String text) {
        this.text = text;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public SaveNotebookRequest setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public SaveNotebookRequest setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }
}

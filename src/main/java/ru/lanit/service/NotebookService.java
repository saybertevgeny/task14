package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ru.lanit.dto.NotebookResponse;
import ru.lanit.dto.SaveNotebookRequest;
import ru.lanit.entity.Notebook;
import ru.lanit.entity.PriorityEnum;
import ru.lanit.repository.NotebookRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotebookService {

    @Autowired
    protected NotebookRepository repository;

    public void save(SaveNotebookRequest request) {
        Notebook entity = new Notebook();
        entity.setTheme(request.getTheme())
                .setText(request.getText())
                .setDeadline(LocalDate.now())
                .setPriority(PriorityEnum.LOW);
        this.repository.save(entity);
    }

    public List<NotebookResponse> getAll(){
        List<NotebookResponse> result = new ArrayList<>();
        List<Notebook> notebooks = this.repository.findAll();
        for(Notebook notebook:notebooks){
            result.add(new NotebookResponse(
                    notebook.getId(),
                    notebook.getTheme(),
                    notebook.getText(),
                    notebook.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
                    notebook.getPriority().name()
            ));
        }
        return result;
    }

    public NotebookResponse getById(int id){
        Notebook notebook = this.repository.findById(id);
        return new NotebookResponse(
                notebook.getId(),
                notebook.getTheme(),
                notebook.getText(),
                notebook.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
                notebook.getPriority().name()
        );
    }
}

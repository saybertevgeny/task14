package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lanit.dto.NotebookResponse;
import ru.lanit.dto.SaveNotebookRequest;
import ru.lanit.entity.Notebook;
import ru.lanit.entity.PriorityEnum;
import ru.lanit.exception.NoEntityException;
import ru.lanit.repository.NotebookRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotebookService {

    @Autowired
    protected NotebookRepository repository;

    public void save(SaveNotebookRequest request) throws NoEntityException {
        Notebook entity;
        if(request.getId() != 0){
            entity = repository.findById(request.getId()).orElseThrow(() -> new NoEntityException());
        }
        else{
            entity = new Notebook();
        }
        entity.setTheme(request.getTheme())
                .setText(request.getText())
                .setDeadline(request.getDeadline())
                .setPriority(PriorityEnum.valueOf(request.getPriority()));

        this.repository.saveAndFlush(entity);
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

    public NotebookResponse getById(int id) throws NoEntityException{
        Notebook notebook = this.repository.findById(id).orElseThrow(() -> new NoEntityException());
        return new NotebookResponse(
                notebook.getId(),
                notebook.getTheme(),
                notebook.getText(),
                notebook.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
                notebook.getPriority().name()
        );
    }

    public void delete(int id) throws NoEntityException{
        Notebook notebook = repository.findById(id).orElseThrow(() -> new NoEntityException());
        this.repository.delete(notebook);
    }
}

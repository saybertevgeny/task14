package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.dto.SaveNotebookRequest;
import ru.lanit.exception.NoEntityException;
import ru.lanit.service.NotebookService;

import javax.validation.Valid;

@RestController
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @RequestMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody @Valid SaveNotebookRequest request) throws NoEntityException {
        try {
            notebookService.save(request);
            return ResponseEntity.ok().build();
        } catch (NoEntityException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(notebookService.getAll());
    }

    @RequestMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable(required = true) int id) {
        return ResponseEntity.ok(notebookService.getById(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete() {
        return ResponseEntity.ok().build();
    }
}

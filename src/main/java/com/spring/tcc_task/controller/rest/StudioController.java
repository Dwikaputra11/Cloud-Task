package com.spring.tcc_task.controller.rest;

import com.spring.tcc_task.models.Studio;
import com.spring.tcc_task.service.StudioService;
import com.spring.tcc_task.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class StudioController {
    private final StudioService studioService;
    private static final String SUCCESS_RETRIEVE_MSG = "Successfully retrieved data!";
    private static final String SUCCESS_EDIT_MSG = "Successfully edit data!";

    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping("/studio")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue ="10") int size
    ){
        Page<Studio> filmList;
        Pageable pageable = PageRequest.of(page, size);
        filmList = studioService.findAll(pageable);

        return ResponseHandler.generatePagingResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,filmList);
    }

    @PostMapping("/studio")
    public ResponseEntity<Object> save(@RequestBody Studio studio){
        studioService.save(studio);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK,studio);
    }

    @GetMapping("/studio/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        var studio = studioService.findById(id);
        return ResponseHandler.generateResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,studio);
    }

    @PutMapping("/studio")
    public ResponseEntity<Object> update(@RequestBody Studio film ) {
        studioService.update(film);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, film);
    }
    @DeleteMapping("/studio/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id ) {
        studioService.delete(id);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, id);
    }

}

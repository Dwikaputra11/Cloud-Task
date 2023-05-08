package com.spring.tcc_task.controller;


import com.spring.tcc_task.models.Staff;
import com.spring.tcc_task.service.StaffService;
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
public class StaffController {

    private final StaffService staffService;
    private static final String SUCCESS_RETRIEVE_MSG = "Successfully retrieved data!";
    private static final String SUCCESS_EDIT_MSG = "Successfully edit data!";

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue ="10") int size
    ){
        Page<Staff> filmList;
        Pageable pageable = PageRequest.of(page, size);
        filmList = staffService.findAll(pageable);

        return ResponseHandler.generatePagingResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,filmList);
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        var staff = staffService.findById(id);
        return ResponseHandler.generateResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,staff);
    }

    @PostMapping("/staff")
    public ResponseEntity<Object> save(@RequestBody Staff film){
        staffService.save(film);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK,film);
    }

    @PutMapping("/staff")
    public ResponseEntity<Object> update(@RequestBody Staff film ) {
        staffService.update(film);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, film);
    }
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id ) {
        staffService.delete(id);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, id);
    }
}

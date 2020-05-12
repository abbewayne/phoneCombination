package com.combination.rest.controller;

import java.util.List;

import com.combination.rest.service.PhoneCombinationServiceI;
import com.combination.rest.util.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(path = "/num")
public class PhoneCombinationController
{
    @Autowired
    private PhoneCombinationServiceI phoneCombinationServiceI;

    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<PaginatedResponse> getListOfPaginatedPhoneCombination(@RequestParam(required = false) int page, @RequestParam(required = false) String phoneNumber) {
        System.out.println("inside controller page: = " + page);
        System.out.println("inside controller phoneNumber: = " + phoneNumber);
        List<String> records = phoneCombinationServiceI.phoneCombinationCalc(phoneNumber);
        PaginatedResponse result = new PaginatedResponse(page, records);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

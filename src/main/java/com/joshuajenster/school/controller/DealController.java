package com.joshuajenster.school.controller;

import com.joshuajenster.school.domain.Deal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/deal")
public class DealController {

    //test method
    @GetMapping("")
    Deal getDeal() {
        return new Deal(1, new Date(), new Date(), new ArrayList<>());
    }

    //test method
    @GetMapping("/{id}")
    Deal getDealById(@PathVariable int id) {
        return new Deal(id, new Date(), new Date(), new ArrayList<>());
    }
}

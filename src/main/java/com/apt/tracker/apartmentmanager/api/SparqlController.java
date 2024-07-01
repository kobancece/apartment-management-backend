package com.apt.tracker.apartmentmanager.api;

import com.apt.tracker.apartmentmanager.service.SparqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apartment/sparql")
@CrossOrigin(origins = "http://localhost:4200")
public class SparqlController {

    @Autowired
    private SparqlService sparqlService;

    @PostMapping("/query")
    public List<Map<String, String>> executeQuery(@RequestBody String query) {
        return sparqlService.executeQuery(query);
    }
}

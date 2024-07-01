package com.apt.tracker.apartmentmanager.service;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SparqlService {

    private Model model;

    public SparqlService() {
        try {
            model = ModelFactory.createDefaultModel();
            // Güncellenmiş dosya yolunu buraya ekleyin
            InputStream in = new FileInputStream("C:\\Users\\HP\\Desktop\\Politechnika_Wroclawska\\Courses\\Information_Modelling\\apartmen-management-backend\\apartment-management-backend\\src\\main\\java\\com\\apt\\tracker\\apartmentmanager\\ontology\\instances.ttl");
            model.read(in, null, "TURTLE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> executeQuery(String queryString) {
        List<Map<String, String>> results = new ArrayList<>();

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet resultSet = qexec.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution soln = resultSet.nextSolution();
                Map<String, String> result = StreamSupport.stream(Spliterators.spliteratorUnknownSize(resultSet.getResultVars().iterator(), Spliterator.ORDERED), false)
                        .collect(Collectors.toMap(
                                varName -> varName,
                                varName -> soln.get(varName).toString()
                        ));
                results.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (results.isEmpty()) {
            Map<String, String> noResult = new HashMap<>();
            noResult.put("message", "No results found");
            results.add(noResult);
        }

        return results;
    }
}

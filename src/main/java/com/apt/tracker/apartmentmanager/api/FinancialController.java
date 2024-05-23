package com.apt.tracker.apartmentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apt.tracker.apartmentmanager.model.Financial;
import com.apt.tracker.apartmentmanager.service.FinancialService;
import java.util.Map;

@RestController
@RequestMapping("/apartment/financial")
public class FinancialController {

    @Autowired
    private FinancialService financialService;

    @PostMapping("/trackrent")
    public ResponseEntity<?> trackRent(@RequestBody Financial financial) {
        financialService.trackRent(financial.getUserID(), financial.getPropertyID(), financial.getRentAmount());
        return ResponseEntity.ok(Map.of("code", 201, "message", "Success."));
    }

    @PostMapping("/trackdues")
    public ResponseEntity<?> trackDues(@RequestBody Financial financial) {
        financialService.trackDues(financial.getUserID(), financial.getPropertyID(), financial.getDuesAmount());
        return ResponseEntity.ok(Map.of("code", 201, "message", "Success."));
    }
}

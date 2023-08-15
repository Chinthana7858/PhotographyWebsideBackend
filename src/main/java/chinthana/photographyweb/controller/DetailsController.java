package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.Details;
import chinthana.photographyweb.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/details")
@AllArgsConstructor
public class DetailsController {
    private final DetailsService detailsService;

    @PostMapping
    public ResponseEntity<Details> saveDetails(@RequestBody Details details) {
        Details savedDetails = detailsService.saveOrUpdateDetails(details);
        return new ResponseEntity<>(savedDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{detailsId}")
    public ResponseEntity<Details> getDetailsById(@PathVariable String detailsId) {
        Details details = detailsService.getDetailsById(detailsId);
        if (details == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PutMapping("/{detailsId}/introduction")
    public ResponseEntity<Details> updateIntroduction(@PathVariable String detailsId, @RequestBody Map<String, String> request) {
        String newIntroduction = request.get("introduction");
        if (newIntroduction == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Details updatedDetails = detailsService.updateIntroduction(detailsId, newIntroduction);
        if (updatedDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

}

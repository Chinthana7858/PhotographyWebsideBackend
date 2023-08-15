package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.Portfolio;
import chinthana.photographyweb.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/portfolio")
@AllArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<?> uploadPortfolio(@RequestParam("portfolioName") String portfolioName,
                                             @RequestParam("about") String about,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("dateTime") LocalDateTime dateTime) throws IOException
    {
        Portfolio savedPhoto = portfolioService.savePortfolio(portfolioName,about,file,dateTime);
        return ResponseEntity.ok(savedPhoto);
    }

    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable String id) {
        Optional<Portfolio> portfolio = portfolioService.getPortfolioById(id);
        return portfolio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable String id) {
        portfolioService.deletePortfolio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/latest2")
    public ResponseEntity<List<Portfolio>> getTwoLatestPortfolios() {
        List<Portfolio> latestPortfolios = portfolioService.getTwoLatestPortfolios();
        if (latestPortfolios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latestPortfolios, HttpStatus.OK);
    }

}

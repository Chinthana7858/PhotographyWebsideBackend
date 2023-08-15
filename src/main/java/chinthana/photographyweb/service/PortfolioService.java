package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.CategoryPhoto;
import chinthana.photographyweb.entity.Portfolio;
import chinthana.photographyweb.repository.PortfolioRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PortfolioService {
    @Autowired
    private final PortfolioRepository portfolioRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Portfolio savePortfolio(String portfolioName,String about, MultipartFile file, LocalDateTime dateTime) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String coverImageUrl = uploadResult.get("url").toString();
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioName(portfolioName);
        portfolio.setAbout(about);
        portfolio.setDateTime(dateTime);
        portfolio.setCoverImgUrl(coverImageUrl);
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getPortfolioById(String id) {
        return portfolioRepository.findById(id);
    }

    public void deletePortfolio(String id) {
        portfolioRepository.deleteById(id);
    }

    // Service method to get the two latest portfolios
    public List<Portfolio> getTwoLatestPortfolios() {
        return portfolioRepository.findTop2ByOrderByDateTimeDesc();
    }

}

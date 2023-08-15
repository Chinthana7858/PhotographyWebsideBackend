package chinthana.photographyweb.service;

import chinthana.photographyweb.entity.Details;
import chinthana.photographyweb.repository.DetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DetailsService {
    @Autowired
    private final DetailsRepository detailsRepository;

    public Details saveOrUpdateDetails(Details details) {
        return detailsRepository.save(details);
    }

    public Details getDetailsById(String detailsId) {
        return detailsRepository.findById(detailsId).orElse(null);
    }

    public Details updateIntroduction(String detailsId, String newIntroduction) {
        Details details = detailsRepository.findById(detailsId).orElse(null);
        if (details != null) {
            details.setIntroduction(newIntroduction);
            return detailsRepository.save(details);
        }
        return null;
    }
}

package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.Studio;
import com.spring.tcc_task.repos.StudioRepository;
import com.spring.tcc_task.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }
    @Override
    public Page<Studio> findAll(Pageable pageable) {
        return studioRepository.findAll(pageable);
    }

    @Override
    public Studio findById(int id) {
        var studio = studioRepository.findById(id);
        if(studio.isEmpty()) throw new RuntimeException("Data studio id: " + id + " is not exist.");
        return studio.get();
    }

    @Override
    public Studio save(Studio studio) {
        if (studio.getName() == null || studio.getName().isEmpty())
            throw new RuntimeException("Data studio is not valid");
//        var date = Calendar.getInstance();
//        studio.setLastUpdate(date.getTime());
        studio.setStudioId(0);
        return studioRepository.save(studio);
    }

    @Override
    public Studio update(Studio updatedStudio) {
        var result = studioRepository.findById(updatedStudio.getStudioId());

        if(result.isEmpty())
            throw new RuntimeException("Data studio id: " + updatedStudio.getStudioId() + " is not exist.");

        var costumer = result.get();
        costumer.setName(updatedStudio.getName());

        return studioRepository.save(costumer);
    }

    @Override
    public void delete(int id) {
        var studio = studioRepository.findById(id);
        if(studio.isEmpty()) throw new RuntimeException("Data studio id: " + id + " is not exist.");
        studioRepository.delete(studio.get());
    }
}

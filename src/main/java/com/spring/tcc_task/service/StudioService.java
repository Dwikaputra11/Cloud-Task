package com.spring.tcc_task.service;

import com.spring.tcc_task.models.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudioService {
    Page<Studio> findAll(Pageable pageable);

    Studio findById(int id);

    Studio save(Studio studio);

    Studio update(Studio updatedStudio);

    void delete(int id);
}

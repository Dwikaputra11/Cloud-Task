package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.Costumer;
import com.spring.tcc_task.repos.CostumerRepository;
import com.spring.tcc_task.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class CostumerServiceImpl implements CostumerService {

    private final CostumerRepository costumerRepository;
    @Autowired
    public CostumerServiceImpl(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }
    @Override
    public Page<Costumer> findAll(Pageable pageable) {
        return costumerRepository.findAll(pageable);
    }

    @Override
    public Costumer findById(int id) {
        var costumer = costumerRepository.findById(id);
        if(costumer.isEmpty()) throw new RuntimeException("Data costumer id: " + id + " is not exist.");
        return costumer.get();
    }

    @Override
    public Costumer save(Costumer costumer) {
        if (costumer.getUsername() == null || costumer.getUsername().isEmpty()
                || costumer.getEmail() == null || costumer.getEmail().isEmpty()
        )  throw new RuntimeException("Data costumer is not valid");

        costumer.setCostumerId(0);

        var date = Calendar.getInstance();
        costumer.setLastUpdate(date.getTime());

        return costumerRepository.save(costumer);
    }

    @Override
    public Costumer update(Costumer updatedCostumer) {
        var result = costumerRepository.findById(updatedCostumer.getCostumerId());

        if(result.isEmpty())
            throw new RuntimeException("Data costumer id: " + updatedCostumer.getCostumerId() + " is not exist.");

        var costumer = result.get();
        costumer.setEmail(updatedCostumer.getEmail());
        costumer.setUsername(updatedCostumer.getUsername());
        return costumerRepository.save(costumer);
    }

    @Override
    public void delete(int id) {
        var costumer = costumerRepository.findById(id);
        if(costumer.isEmpty()) throw new RuntimeException("Data costumer id: " + id + " is not exist.");
        costumerRepository.delete(costumer.get());
    }
}

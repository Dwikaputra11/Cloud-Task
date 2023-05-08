package com.spring.tcc_task.service;


import com.spring.tcc_task.dto.PaymentRequestDTO;
import com.spring.tcc_task.dto.PaymentResponseDTO;
import com.spring.tcc_task.models.Payment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService {

    Page<Payment> findAll(Pageable pageable);

    List<PaymentResponseDTO> findAll();

    PaymentResponseDTO findById(int id);

    JasperPrint exportReport(int id) throws JRException, IOException;

    PaymentResponseDTO save(PaymentRequestDTO payment);

    PaymentResponseDTO update(PaymentRequestDTO updatedPayment);

    void delete(int id);
}

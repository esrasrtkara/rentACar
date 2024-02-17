package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    @Query("SELECT MAX(i.invoiceNo) FROM Invoice i")
    Long findMaxInvoiceNumber();

    Optional<Invoice> findByRentalId(int rentalId);
}

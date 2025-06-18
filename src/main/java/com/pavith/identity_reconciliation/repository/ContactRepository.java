package com.pavith.identity_reconciliation.repository;

import com.pavith.identity_reconciliation.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);
    List<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByEmail(String email);
    List<Contact> findByPhoneNumberAndEmail(String phoneNumber, String email);

}

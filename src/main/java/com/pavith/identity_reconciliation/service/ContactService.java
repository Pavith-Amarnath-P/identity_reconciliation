package com.pavith.identity_reconciliation.service;

import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.model.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact (IdentityRequest identityRequest);
    Contact updateContact (Contact contact);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);
    List<Contact> findByPhoneNumberOrderByCreatedAtAsc(String phoneNumber);
    List<Contact> findByEmailOrderByCreatedAtAsc(String email);
    List<Contact> findByPhoneNumberAndEmailOrderByCreatedAtAsc(String phoneNumber, String email);
}

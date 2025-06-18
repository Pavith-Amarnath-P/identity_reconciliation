package com.pavith.identity_reconciliation.service;

import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.model.Contact;

public interface ContactService {
    Contact createContact (IdentityRequest identityRequest);
    Contact updateContact (IdentityRequest identityRequest);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);
}

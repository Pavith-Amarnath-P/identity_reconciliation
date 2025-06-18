package com.pavith.identity_reconciliation.service.impl;

import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.model.Contact;
import com.pavith.identity_reconciliation.repository.ContactRepository;
import com.pavith.identity_reconciliation.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public Contact createContact(IdentityRequest identityRequest) {
        return null;
    }

    @Override
    public Contact updateContact(IdentityRequest identityRequest) {
        return null;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return contactRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return contactRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumberAndEmail(String phoneNumber, String email) {
        return contactRepository.existsByPhoneNumberAndEmail(phoneNumber, email);
    }
}

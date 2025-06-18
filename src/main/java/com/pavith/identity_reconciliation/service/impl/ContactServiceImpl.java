package com.pavith.identity_reconciliation.service.impl;

import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.model.Contact;
import com.pavith.identity_reconciliation.repository.ContactRepository;
import com.pavith.identity_reconciliation.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public Contact createContact(IdentityRequest identityRequest) {
        Contact contact = new Contact();
        contact.setEmail(identityRequest.getEmail());
        contact.setPhoneNumber(identityRequest.getPhoneNumber());
        contact.setLinkedId(null);
        contact.setLinkPrecedence(Contact.Precedence.PRIMARY);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setUpdatedAt(LocalDateTime.now());
        contact.setDeletedAt(null);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = new Contact();
        existingContact.setId(contact.getId());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setEmail(contact.getEmail());
        existingContact.setLinkedId(contact.getLinkedId());
        existingContact.setLinkPrecedence(contact.getLinkPrecedence());
        existingContact.setCreatedAt(contact.getCreatedAt());
        existingContact.setUpdatedAt(LocalDateTime.now());
        existingContact.setDeletedAt(contact.getDeletedAt());
        return contactRepository.save(existingContact);
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

    @Override
    public List<Contact> findByPhoneNumberOrderByCreatedAtAsc(String phoneNumber) {
        return contactRepository.findByPhoneNumberOrderByCreatedAtAsc(phoneNumber);
    }

    @Override
    public List<Contact> findByEmailOrderByCreatedAtAsc(String email) {
        return contactRepository.findByEmailOrderByCreatedAtAsc(email);
    }

    @Override
    public List<Contact> findByPhoneNumberAndEmailOrderByCreatedAtAsc(String phoneNumber, String email) {
        return contactRepository.findByPhoneNumberAndEmailOrderByCreatedAtAsc(phoneNumber, email);
    }
}

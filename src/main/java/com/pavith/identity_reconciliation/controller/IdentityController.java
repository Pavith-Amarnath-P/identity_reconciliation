package com.pavith.identity_reconciliation.controller;

import com.pavith.identity_reconciliation.dto.ContactResponse;
import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.dto.IdentityResponse;
import com.pavith.identity_reconciliation.model.Contact;
import com.pavith.identity_reconciliation.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/identify")
@RequiredArgsConstructor
public class IdentityController {
    private final ContactService contactService;
    @PostMapping
    public ResponseEntity<IdentityResponse> identify(@RequestBody    IdentityRequest identityRequest) {
        String phoneNumber = identityRequest.getPhoneNumber();
        String email = identityRequest.getEmail();
        if (phoneNumber == null && email == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean existsByPhoneNumber = false;
        boolean existsByEmail = false;
        boolean existsByPhoneNumberAndEmail = false;
        if (phoneNumber != null) {
            existsByPhoneNumber = contactService.existsByPhoneNumber(phoneNumber);
        }
        if (email != null) {
            existsByEmail = contactService.existsByEmail(email);
        }
        if (phoneNumber != null && email != null) {
            existsByPhoneNumberAndEmail = contactService.existsByPhoneNumberAndEmail(phoneNumber, email);
        }
        if (!existsByPhoneNumber && !existsByEmail){
            // Create new contact and make this primary.
            Contact savedContact = contactService.createContact(identityRequest);
            IdentityResponse identityResponse = new IdentityResponse();
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setPrimaryContactId(savedContact.getId());
            contactResponse.setEmails(List.of(savedContact.getEmail()));
            contactResponse.setPhoneNumbers(List.of(savedContact.getPhoneNumber()));
            contactResponse.setSecondaryContactIds(List.of());
            identityResponse.setContact(contactResponse);
            return ResponseEntity.ok().body(identityResponse);
        } else if (!existsByEmail || !existsByPhoneNumber){
            // Existing will remain primary.
            // If request contain new info, create new contact and link to existing.
            Contact existingContact = existsByEmail ? contactService.findByEmailOrderByCreatedAtAsc(identityRequest.getEmail()).getFirst() : contactService.findByPhoneNumberOrderByCreatedAtAsc(identityRequest.getPhoneNumber()).getFirst();
            Contact newContact = contactService.createContact(identityRequest);
            newContact.setLinkedId(existingContact.getId());
            newContact.setLinkPrecedence(Contact.Precedence.SECONDARY);
            Contact updatedContact = contactService.updateContact(newContact);
            HashSet<String> emails = new LinkedHashSet<>();
            emails.add(existingContact.getEmail());
            emails.add(updatedContact.getEmail());
            HashSet<String> phoneNumbers = new LinkedHashSet<>();
            phoneNumbers.add(existingContact.getPhoneNumber());
            phoneNumbers.add(updatedContact.getPhoneNumber());
            IdentityResponse identityResponse = new IdentityResponse();
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setPrimaryContactId(existingContact.getId());
            contactResponse.setEmails(new ArrayList<>(emails));
            contactResponse.setPhoneNumbers(new ArrayList<>(phoneNumbers));
            contactResponse.setSecondaryContactIds(List.of(updatedContact.getId()));
            identityResponse.setContact(contactResponse);
            return ResponseEntity.ok().body(identityResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}

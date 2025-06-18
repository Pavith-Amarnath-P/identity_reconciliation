package com.pavith.identity_reconciliation.controller;

import com.pavith.identity_reconciliation.dto.IdentityRequest;
import com.pavith.identity_reconciliation.dto.IdentityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identify")
public class IdentityController {
    @PostMapping
    public ResponseEntity<IdentityResponse> identify(IdentityRequest identityRequest) {

    }
}

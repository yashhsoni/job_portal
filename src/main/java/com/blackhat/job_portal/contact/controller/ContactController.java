package com.blackhat.job_portal.contact.controller;

import com.blackhat.job_portal.contact.service.IContactService;
import com.blackhat.job_portal.dto.ContactRequestDto;
import com.blackhat.job_portal.repository.ContactRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0")
    public ResponseEntity<String> saveContactMsg(@RequestBody @Valid ContactRequestDto contactRequestDto) {
        boolean isSaved = contactService.saveContact(contactRequestDto);

        if (isSaved) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request Processed Successfull");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request Processing Failed");
        }
    }

    @GetMapping
    public ResponseEntity<String> fetchOpenContacts(@RequestParam @Validated @NotBlank (message = "Status cannot be blanked") String status){
        return ResponseEntity.ok("These are the contact with given status" + status);
    }
}

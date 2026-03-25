package com.blackhat.job_portal.contact.service;

import com.blackhat.job_portal.dto.ContactRequestDto;
import com.blackhat.job_portal.entity.Contact;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}

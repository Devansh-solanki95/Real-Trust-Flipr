package com.Flipr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Flipr.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

}

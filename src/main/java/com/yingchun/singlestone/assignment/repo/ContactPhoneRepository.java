package com.yingchun.singlestone.assignment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.singlestone.assignment.model.ContactPhone;

@Repository("h2ContactPhoneRepo")
public interface ContactPhoneRepository extends CrudRepository<ContactPhone, Integer>{
}

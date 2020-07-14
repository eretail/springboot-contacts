package com.yingchun.singlestone.assignment.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.singlestone.assignment.model.Contact;

@Repository("h2ContactRepo")
@Transactional
public interface ContactRepository extends CrudRepository<Contact, Integer>{
/*
	@Query
	List<Contact> getAll();

	Contact addOne(Integer id);
	Contact addOne(Contact id);

	@Query
	Contact deleteOneById(Integer id);
*/
}

package com.javan.tugas4.repository;

import com.javan.tugas4.data.DataCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<DataCompany, Integer> { }
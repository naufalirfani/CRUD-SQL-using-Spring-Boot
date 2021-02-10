package com.javan.tugas4.repository;

import com.javan.tugas4.data.DataEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<DataEmployee, Integer> { }
package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartamentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartamentRepository departamentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
        Page<Employee> list = repository.findAll(pageable);
        return list.map(EmployeeDTO::new);
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee entity = new Employee();
        copyToEntity(dto, entity);
        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }

    private void copyToEntity(EmployeeDTO dto, Employee entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        Department department = departamentRepository.getReferenceById(dto.getDepartmentId());
        entity.setDepartment(department);
    }
}
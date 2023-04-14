package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartamentRepository repository;

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        List<Department> list = repository.findAll(sort);
        return list.stream().map(DepartmentDTO::new).collect(Collectors.toList());
    }
}

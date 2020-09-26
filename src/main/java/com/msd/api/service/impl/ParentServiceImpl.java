package com.msd.api.service.impl;

import com.msd.api.domain.Parent;
import com.msd.api.dto.ParentChildDto;
import com.msd.api.dto.ParentDto;
import com.msd.api.repository.ParentRepository;
import com.msd.api.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent addParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public List<ParentDto> findParentData(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        return parentRepository.findAll(pageable).stream().map(ParentDto :: new ).collect(Collectors.toList());
    }

    @Override
    public List<ParentChildDto> findChildDetails(int parentId) {
        return parentRepository.findById(parentId).get().getChildList().stream().map(ParentChildDto:: new).collect(Collectors.toList());
    }


}

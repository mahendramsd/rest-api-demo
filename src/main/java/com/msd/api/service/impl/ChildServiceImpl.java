package com.msd.api.service.impl;

import com.msd.api.domain.Child;
import com.msd.api.domain.Parent;
import com.msd.api.dto.ChildDto;
import com.msd.api.exception.CustomException;
import com.msd.api.repository.ChildRepository;
import com.msd.api.repository.ParentRepository;
import com.msd.api.service.ChildService;
import com.msd.api.util.CustomErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository, ParentRepository parentRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }


    @Override
    public void addChild(ChildDto childDto) {
        Optional<Parent> parent = parentRepository.findById(childDto.getParentId());
        if (parent.isPresent()) {
            Child child = new Child();
            child.setId(childDto.getId());
            child.setPaidAmount(childDto.getPaidAmount());
            child.setParentId(parent.get());
            childRepository.save(child);
        } else {
            throw new CustomException(CustomErrorCodes.PARENT_NOT_FOUND);
        }
    }

    @Override
    public void removeAll() {
        childRepository.deleteAll();
    }

    @Override
    public List<Child> findAll() {
        return childRepository.findAll();
    }
}

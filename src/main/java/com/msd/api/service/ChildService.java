package com.msd.api.service;

import com.msd.api.domain.Child;
import com.msd.api.domain.Parent;
import com.msd.api.dto.ChildDto;

import java.util.List;

public interface ChildService {

    List<Child> findAll();

    void addChild(ChildDto child);

    void removeAll();
}

package com.msd.api.service;

import com.msd.api.domain.Parent;
import com.msd.api.dto.ParentChildDto;
import com.msd.api.dto.ParentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ParentService {

    Parent addParent(Parent parent);


    List<ParentDto> findParentData(int page, int limit);

    List<ParentChildDto> findChildDetails(int parentId);
}

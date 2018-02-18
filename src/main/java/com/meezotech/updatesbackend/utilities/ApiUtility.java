package com.meezotech.updatesbackend.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ApiUtility {

    public static PageRequest getPageRequestWithSorting(Pageable pageable, String sortProperty) {
        return new PageRequest(
                pageable.getPageNumber(), pageable.getPageSize(), new Sort(
                new Sort.Order(Sort.Direction.DESC, sortProperty)));
    }

}

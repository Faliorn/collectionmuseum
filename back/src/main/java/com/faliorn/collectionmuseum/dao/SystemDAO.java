package com.faliorn.collectionmuseum.dao;

import java.util.ArrayList;
import java.util.List;

import com.faliorn.collectionmuseum.model.System;

public class SystemDAO {
    ArrayList<System> all;

    public SystemDAO() {
        all = new ArrayList<>();
        all.add(new System(1L, "uno"));
        all.add(new System(2L, "dos"));
        all.add(new System(3L, "tres"));
    }

    public List<System> findAll() {
        return all;
    }

    public System findById(Long id) {
        return all.stream().filter(sys -> sys.getId().equals(id)).findAny().get();
    }
}
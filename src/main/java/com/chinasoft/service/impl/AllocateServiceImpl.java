package com.chinasoft.service.impl;

import com.chinasoft.dao.AllocateDao;
import com.chinasoft.po.Allocate;
import com.chinasoft.service.AllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocateServiceImpl implements AllocateService {

    @Autowired
    private AllocateDao allocateDao;

    @Override
    public int insert(Allocate allocate) {
        if (allocate == null) {
            return 0;
        }
        return allocateDao.insert(allocate);
    }

    @Override
    public List<Allocate> listByExpertId(Long id) {
        if (id != null) {
            List<Allocate> allocates = allocateDao.listByExpertId(id);
            return allocates;
        }
        return null;
    }

    @Override
    public List<Allocate> listByGroupId(Long groupId) {
        if (groupId != null) {
            List<Allocate> allocates = allocateDao.listByGroupId(groupId);
            return allocates;
        }
        return null;
    }

}

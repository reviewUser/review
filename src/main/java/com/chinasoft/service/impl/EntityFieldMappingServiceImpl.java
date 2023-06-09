package com.chinasoft.service.impl;

import com.chinasoft.dao.EntityFieldMappingDao;
import com.chinasoft.po.EntityField;
import com.chinasoft.service.EntityFieldMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用于评审任务申请、专家与领域相关联的业务层接口
 */

@Service
public class EntityFieldMappingServiceImpl implements EntityFieldMappingService {

    @Autowired
    private EntityFieldMappingDao entityFieldMappingDao;

    /**
     * 添加关联
     *
     * @param entityField
     */
    @Override
    public int insert(EntityField entityField) {
        if (entityField != null) {
            return entityFieldMappingDao.insert(entityField);
        }
        return 0;
    }

    /**
     * 根据projectId查询
     *
     * @param id
     * @return
     */
    @Override
    public List<EntityField> listByEntityId(Long id, String entityType) {
        if (id != null) {
            List<EntityField> entityFields = entityFieldMappingDao.listByEntityId(id, entityType);
            return entityFields;
        }
        return null;
    }

}

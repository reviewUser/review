package com.chinasoft.service.impl;

import com.chinasoft.dao.FieldDao;
import com.chinasoft.exception.CustomException;
import com.chinasoft.param.FieldsListParam;
import com.chinasoft.utils.ListUtils;
import com.chinasoft.po.Field;
import com.chinasoft.po.SysUser;
import com.chinasoft.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 领域标签业务层接口实现
 */
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    /**
     * 添加领域
     *
     * @param field
     */
    @Override
    public void insert(Field field, SysUser user) {
        if (field != null) {
            field.setCreateTime(new Date());
            field.setLastUpdateTime(new Date());
            field.setCreateUserId(user.getId());
            fieldDao.insert(field);
        }
    }

    /**
     * 批量添加领域
     *
     * @param fields
     */
    @Override
    public void insertBatch(List<Field> fields) {
        if (!CollectionUtils.isEmpty(fields)) {
            fieldDao.insertBatch(fields);
        }
    }

    /**
     * 生成有效领域标签、插入记录同时关联申请记录
     *
     * @param inputFields
     * @param createUserId
     */
    @Override
    public void getValidFieldAndInsertBatch(List<String> inputFields, long createUserId) {
        if (CollectionUtils.isEmpty(inputFields)) {
            throw new CustomException("领域标签不能为空", "/project/uploadPage");
        }
        List<Field> validFields = new ArrayList<Field>(); // 最后真正进行插入的有效领域
        for (String string : inputFields) {
            Field field = new Field();
            field.setFieldName(string);
            field.setCreateTime(new Date());
            field.setCreateUserId(createUserId);
            validFields.add(field);
        }
        List<Field> existFields = fieldDao.listFieldsByNames(inputFields);
        // 如果数据库中已存在相同标签
        if (!CollectionUtils.isEmpty(existFields))
            validFields = ListUtils.removeAll(validFields, existFields);
        if (!CollectionUtils.isEmpty(validFields))
            insertBatch(validFields);
    }

    /**
     * 得到总记录数
     *
     * @param param
     * @return
     */
    @Override
    public int getTotalCount(FieldsListParam param) {
        if (param != null) {
            return fieldDao.getTotalCount(param);
        }
        return 0;
    }

    /**
     * 查询标签
     *
     * @param param
     * @return
     */
    @Override
    public List<Field> listField(FieldsListParam param) {
        if (param != null) {
            return fieldDao.listField(param);
        }
        return null;
    }

    /**
     * 修改领域标签信息
     */
    @Override
    public int update(Field field) {
        if (field != null) {
            return fieldDao.update(field);
        }
        return 0;
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Field getById(Long id) {
        if (id == null) {
            return null;
        }
        Field field = fieldDao.getById(id);
        return field;
    }

    /**
     * 根据id删除领域
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        if (id != null) {
            fieldDao.deleteById(id);
        }
    }

    /**
     * 根据ID列表查询
     *
     * @param fieldIds
     * @return
     */
    @Override
    public List<Field> listByIds(List<Long> fieldIds) {
        if (!CollectionUtils.isEmpty(fieldIds)) {
            List<Field> fields = fieldDao.listByIds(fieldIds);
            return fields;
        }
        return null;
    }
}

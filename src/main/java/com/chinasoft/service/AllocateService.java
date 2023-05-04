package com.chinasoft.service;

import com.chinasoft.po.Allocate;

import java.util.List;

/**
 * 分配处理接口
 * @author wangye
 *
 */
public interface AllocateService {

	int insert(Allocate allocate);

	List<Allocate> listByExpertId(Long id);

	List<Allocate> listByGroupId(Long groupId);
}

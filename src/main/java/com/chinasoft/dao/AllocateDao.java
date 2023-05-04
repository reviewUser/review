package com.chinasoft.dao;

import com.chinasoft.po.Allocate;
import com.chinasoft.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AllocateDao {

	int insert(Allocate allocate);

	List<Allocate> listByExpertId(Long id);

	List<Allocate> listByGroupId(Long groupId);

	@Select("Select * from sys_user where id = #{id}")
	SysUser getUserInfo(@Param("id")Long id);
}

package com.huarun.dao;

import com.huarun.pojo.ActionRoleFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * url权限控制与角色控制
 */
@Repository
public interface ActionRoleFilterMapper {

    List<ActionRoleFilter> selectAll();

}

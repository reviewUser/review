package com.chinasoft.po;

import lombok.Data;

/**
 * 评审任务申请、专家与领域相关联持久类
 */
@Data
public class EntityField {

    // 关联实体的类型
    public static final String ENTITY_TYPE_PROJECT = "PROJECT";
    public static final String ENTITY_TYPE_EXPERT = "EXPERT";
    private long id;
    private long fieldId; // 领域id
    private long entityId; // 关联的实体id
    private String entityType; // 关联实体的类型
}

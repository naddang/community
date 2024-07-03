package com.dev_cbj.community.comm_code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String code;
    private String parent;
    private String name;
    private String desc;
    private int depth;
    private int order;
    private String useYn;
    private String etc;
    private String indt;
    private String updt;
    private int inUser;
    private int upUser;
}

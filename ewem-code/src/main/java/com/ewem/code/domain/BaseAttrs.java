package com.ewem.code.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseAttrs implements Serializable {

    private static final long serialVersionUID = -819806764645553008L;

    private String k;

    private Object v;

}

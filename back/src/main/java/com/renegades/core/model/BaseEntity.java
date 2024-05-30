package com.renegades.core.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

public abstract class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    public abstract ObjectId getId();
}
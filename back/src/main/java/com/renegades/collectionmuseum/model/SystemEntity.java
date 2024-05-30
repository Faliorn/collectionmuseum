package com.renegades.collectionmuseum.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.renegades.core.model.BaseEntity;

@Document(collection = "systems")
public class SystemEntity extends BaseEntity {

    @Id
    private ObjectId id;
    private String name;

    public SystemEntity() {
        super();
    }

    public SystemEntity(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
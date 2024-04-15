package com.faliorn.collectionmuseum.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class System {

    private Long id;
    private String name;

    public System() {
        super();
    }

    public System(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.faliorn.collectionmuseum.api;

import java.util.List;

import com.faliorn.collectionmuseum.dao.SystemDAO;
import com.faliorn.collectionmuseum.model.System;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/systems")
public class SystemRestWS extends BaseRestWS {
    public SystemRestWS() {
    }

    @GET
    @Path("/")
    public Response getAll() {
        SystemDAO systemDAO = new SystemDAO();
        List<System> all = systemDAO.findAll();
        return Response.ok().entity(all.toArray()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        SystemDAO systemDAO = new SystemDAO();
        System system = systemDAO.findById(id);
        return Response.ok().entity(system).build();
    }
}
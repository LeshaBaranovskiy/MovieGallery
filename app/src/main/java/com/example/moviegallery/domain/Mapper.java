package com.example.moviegallery.domain;

public interface Mapper<ENTITY, MODEL> {
    MODEL transform(ENTITY entity);
}

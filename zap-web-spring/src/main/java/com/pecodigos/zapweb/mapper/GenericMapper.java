package com.pecodigos.zapweb.mapper;

import java.util.List;

public abstract class GenericMapper<E, D> {
    public abstract D toDto(E entity);
    public abstract E toEntity(D dto);

    public List<D> toDtoList(List<E> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    public List<E> toEntityList(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}

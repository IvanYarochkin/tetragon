package com.yarachkin.tetragon.tetragonservice.service;

import com.yarachkin.tetragon.dto.TetragonDto;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.tetragonservice.exception.ServiceTetragonException;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;

import java.util.List;
import java.util.Optional;

public interface TetragonService {

    List<Tetragon> findAll();

    void create(TetragonDto tetragonDto) throws UtilTetragonException, ServiceTetragonException;

    Optional<Tetragon> findById(long id);

    boolean update(long id, TetragonDto tetragonDto) throws ServiceTetragonException;

    boolean delete(long id) throws ServiceTetragonException;
}

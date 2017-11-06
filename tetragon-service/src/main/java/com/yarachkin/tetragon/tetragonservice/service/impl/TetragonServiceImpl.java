package com.yarachkin.tetragon.tetragonservice.service.impl;

import com.yarachkin.tetragon.dao.TetragonDao;
import com.yarachkin.tetragon.dao.exception.DaoTetragonException;
import com.yarachkin.tetragon.dao.impl.TetragonDaoImpl;
import com.yarachkin.tetragon.dto.TetragonDto;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.tetragonservice.exception.ServiceTetragonException;
import com.yarachkin.tetragon.tetragonservice.service.TetragonService;
import com.yarachkin.tetragon.util.converter.TetragonConverter;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;
import com.yarachkin.tetragon.util.validator.TetragonValidator;

import java.util.List;
import java.util.Optional;

public class TetragonServiceImpl implements TetragonService {

    @Override
    public List<Tetragon> findAll() {
        TetragonDao tetragonDao = new TetragonDaoImpl();

        return tetragonDao.findAll();
    }

    @Override
    public void create(TetragonDto tetragonDto) throws ServiceTetragonException {
        try {
            if ( TetragonValidator.validate(tetragonDto) ) {
                TetragonDao tetragonDao = new TetragonDaoImpl();
                tetragonDao.create(TetragonConverter.convert(tetragonDto));
            }
        } catch (DaoTetragonException | UtilTetragonException e) {
            throw new ServiceTetragonException(e);
        }
    }

    @Override
    public Optional<Tetragon> findById(long id) {
        TetragonDao tetragonDao = new TetragonDaoImpl();

        return tetragonDao.findById(id);
    }

    @Override
    public boolean update(long id, TetragonDto tetragonDto) throws ServiceTetragonException {
        try {
            TetragonDao tetragonDao = new TetragonDaoImpl();
            Optional<Tetragon> tetragon = tetragonDao.findById(id);

            if ( !tetragon.isPresent() ) {
                return false;
            }

            return tetragonDao.update(id, TetragonConverter.convert(tetragon.get(), tetragonDto));

        } catch (DaoTetragonException e) {
            throw new ServiceTetragonException(e);
        }
    }

    @Override
    public boolean delete(long id) throws ServiceTetragonException {
        try {
            TetragonDao tetragonDao = new TetragonDaoImpl();

            return tetragonDao.delete(id);

        } catch (DaoTetragonException e) {
            throw new ServiceTetragonException(e);
        }
    }
}

package com.dev_cbj.community.comm_code.repository;

import com.dev_cbj.community.comm_code.entity.CommCodeEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CommCodeRepository implements JpaRepository<CommCodeEntity, Integer>{
    @Override
    public void flush() {

    }

    @Override
    public <S extends CommCodeEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CommCodeEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CommCodeEntity getOne(Integer integer) {
        return null;
    }

    @Override
    public CommCodeEntity getById(Integer integer) {
        return null;
    }

    @Override
    public CommCodeEntity getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CommCodeEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CommCodeEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CommCodeEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CommCodeEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CommCodeEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<CommCodeEntity> findAll() {
        return null;
    }

    @Override
    public List<CommCodeEntity> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(CommCodeEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends CommCodeEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CommCodeEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CommCodeEntity> findAll(Pageable pageable) {
        return null;
    }
}

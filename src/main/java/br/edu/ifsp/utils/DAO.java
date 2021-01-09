package br.edu.ifsp.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K>{

    boolean create(T type);

    Optional<T> findOne(K key);

    List<T> findAll();

    boolean update(T type);

    boolean deleteByKey(K key);

    boolean delete(T type);

}

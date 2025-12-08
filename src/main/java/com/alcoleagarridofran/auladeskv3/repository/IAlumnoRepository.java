package com.alcoleagarridofran.auladeskv3.repository;

import com.alcoleagarridofran.auladeskv3.model.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {
    Optional<Alumno> findByNre(int id);
    Optional<Alumno> deleteByNre(int id);

    void deleteByNre(Integer nre);
}

package com.alcoleagarridofran.auladeskv3.repository;

import com.alcoleagarridofran.auladeskv3.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfesorRepository extends JpaRepository<Profesor, Integer> {
    Optional<Profesor> findByIdProfesor(int id);
}

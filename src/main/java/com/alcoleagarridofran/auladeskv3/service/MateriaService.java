package com.alcoleagarridofran.auladeskv3.service;

import com.alcoleagarridofran.auladeskv3.model.Materia;
import com.alcoleagarridofran.auladeskv3.repository.IMateriaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MateriaService {

    final IMateriaRepository materiaRepository;

    public Materia insertarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public Materia actualizarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }
    public void eliminarMateria(Integer id) {
        materiaRepository.deleteById(id);
    }
}

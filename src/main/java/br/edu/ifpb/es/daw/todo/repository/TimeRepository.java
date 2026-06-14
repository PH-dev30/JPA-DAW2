package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {

}

package demo.mannam_project.repository;

import demo.mannam_project.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long > {


    Optional<Mark> findByMarkname(String markname);

}

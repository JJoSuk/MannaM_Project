package demo.mannam_project.repository;

import demo.mannam_project.domain.File;
import org.hibernate.boot.model.source.spi.AbstractAttributeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long > {

}

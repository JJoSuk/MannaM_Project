package demo.mannam_project.service;

import demo.mannam_project.domain.FileDTO;
import demo.mannam_project.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public String save(FileDTO fileDto) {
        return fileRepository.save(fileDto.toEntity()).getOriginFileName();
    }

}

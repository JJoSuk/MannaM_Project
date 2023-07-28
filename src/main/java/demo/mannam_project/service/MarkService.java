package demo.mannam_project.service;

import demo.mannam_project.domain.Mark;
import demo.mannam_project.domain.MarkDTO;
import demo.mannam_project.repository.MarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MarkService {
    @Autowired
    private MarkRepository markRepository;

    public List<Mark> getMark(){
        // 익명클래스를 사용하면 가독성이 떨어져서 람다식으로 치환해서 사용한다.
//        userRepository.findByUsername(username).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });

        // 검색결과가 없으면 빈 객체를 리턴한다.
        List<Mark> list = markRepository.findAll();

        return list;
    }

    public void insertMark(Mark mark){

        markRepository.save(mark);
    }


    @Transactional
    public Long save(MarkDTO markDTO) {
        return markRepository.save(markDTO.toEntity()).getMid();
    }
}

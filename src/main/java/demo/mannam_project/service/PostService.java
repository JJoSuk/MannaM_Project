package demo.mannam_project.service;

import demo.mannam_project.domain.Post;
import demo.mannam_project.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void insertPost(Post post){
        postRepository.save(post);
    }

//    public List<Post> getPostList(){
//        return postRepository.findAll();
//    }

    public Page<Post> getPostList(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    public Post getPost(int id) {
        return postRepository.findById(id).get();
    }
}

package demo.mannam_project.board.controller;


import demo.mannam_project.board.dto.BoardDTO;
import demo.mannam_project.board.dto.CommentDTO;
import demo.mannam_project.board.service.BoardService;
import demo.mannam_project.board.service.CommentService;
import demo.mannam_project.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm() {
        return "user/board/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO,
                       @PageableDefault(page=1) Pageable pageable,
                       Model model,
                       HttpSession session) throws IOException {
        User user = (User) session.getAttribute("principal");
        boardDTO.setUser(user);
        boardService.save(boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "redirect:/board/paging";
    }
//    @PostMapping("/save")
//    public String dummyData(){
//        for(int i=1;i<=100;i++){
//            BoardDTO boardDTO = BoardDTO.builder()
//                    .id((long) i)
//                    .boardWriter("아무개"+i)
//                    .boardPass("1234")
//                    .boardTitle("테스트 제목 - "+i)
//                    .boardContents("테스트 내용입니다~~~~ ["+i+"]")
//                    .build();
//            boardService.save(boardDTO);
//        }
//        return "index";
//    }

//    @GetMapping("/")
//    public String findAll(Model model,
//                          @PageableDefault(page=1) Pageable pageable) {
//        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
//        List<BoardDTO> boardDTOList = boardService.findAll();
//        model.addAttribute("boardList", boardDTOList);
//        model.addAttribute("page", pageable.getPageNumber());
//        return "redirect:/board/paging";
//    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);

        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "user/board/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, @PageableDefault(page=1) Pageable pageable, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "user/board/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, @PageableDefault(page=1) Pageable pageable, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        model.addAttribute("page", pageable.getPageNumber());
        return "redirect:/board/"+boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @PageableDefault(page=1) Pageable pageable, Model model) {
        boardService.delete(id);
        model.addAttribute("page", pageable.getPageNumber());
        return "redirect:/board/paging";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println("pageable = " + pageable);
        System.out.println("boardList.getNumber() = " + boardList.getNumber());
        return "user/board/paging";

    }

}










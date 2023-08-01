package demo.mannam_project.controller;

import demo.mannam_project.domain.FileDTO;
import demo.mannam_project.domain.Mark;
import demo.mannam_project.domain.MarkDTO;
import demo.mannam_project.service.FileService;
import demo.mannam_project.service.MarkService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {

    @Autowired
    ResourceLoader resourceLoader;


    @Autowired
    MarkService markService;

    @Autowired
    FileService fileService;

    @GetMapping("/kakaomap")
    public String kakaomap() {

        return "user/map/kakaomapmain";
    }


    // 마크 Form 이동
    @GetMapping("/auth/kakaomapRegister")
    public String kakaomapRegister(@RequestParam("y") String y, @RequestParam("x") String x, @RequestParam("tel") String tel, @RequestParam("address_name") String address_name, @RequestParam("place_name") String place_name, Model model) {

        System.out.println("y : " + y);
        System.out.println("tel : " + tel);

        ArrayList<String> list = new ArrayList<String>();
        list.add(y);
        list.add(x);
        list.add(tel);
        list.add(address_name);
        list.add(place_name);

        model.addAttribute("item", new ItemRequest());
        model.addAttribute("list", list);

        return "user/map/kakaomapRegister";
    }

    @Getter
    @Setter
    public class ItemRequest {

        private String markname;
        private String markaddress;
        private String markainfo;
        private String category;
        private String tel;
        private String latitude;
        private String longitude;
        private MultipartFile file;
    }



    @PostMapping("/map/kakaomapRegister")
    public String saveFormRequests(@ModelAttribute("item") ItemRequest itemRequest, HttpServletRequest request) throws IOException {

        String repo = "static/markimage/";

        String markname = itemRequest.getMarkname();
        String markaddress = itemRequest.getMarkaddress();
        String markainfo = itemRequest.getMarkainfo();
        String category = itemRequest.getCategory();
        String tel = itemRequest.getTel();
        String latitude = itemRequest.getLatitude();
        String longitude = itemRequest.getLongitude();
        MarkDTO markDTO = MarkDTO.builder()
                .markname(markname)
                .markaddress(markaddress)
                .markainfo(markainfo)
                .category(category)
                .tel(tel)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        if (itemRequest.getFile() != null) {
            MultipartFile file = itemRequest.getFile();
            String fullPath = request.getServletContext().getRealPath("")+ File.separator+repo + file.getOriginalFilename();
//            String fullPath = resourceLoader.getClassLoader().getResource(File.separator+repo +file.getOriginalFilename());



            System.out.println(request.getServletContext());
            System.out.println(request.getServletContext().getRealPath(""));
            System.out.println(fullPath);
            file.transferTo(new File(fullPath));

//            log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
//            log.info("fullPath = {}", fullPath);

            FileDTO fileDto = FileDTO.builder()
                    .originFileName(file.getOriginalFilename())
                    .fullPath(request.getServletContext().getRealPath("")+ File.separator+repo + file.getOriginalFilename())
                    .build();
            String savedMarkimage = fileService.save(fileDto);
            markDTO.setMarkimage(savedMarkimage);
        }

        markService.save(markDTO);

        return "user/map/kakaomapmain";
    }

//    @PostMapping("/auth/kakaomapRegister")
//    public @ResponseBody ResponseDTO<?> insermark(@RequestBody Mark mark) {
//
//
//        markService.insertMark(mark);
//
//        return new ResponseDTO<>(HttpStatus.OK.value(), mark.getMarkname() + "등록 완료!!");
//
//    }


    @GetMapping("/kakaomarkmap")
    public String kakaomarkmap(Model model){

        List<Mark> list = markService.getMark();
        System.out.println("list = " + list);

        model.addAttribute("list",list);


//        return "user/map/kakaomarkmap";
        return "user/map/kakaomarkmap2";
    }

    @GetMapping("/markupdate/{mid}")
    public String markupdate(@PathVariable int mid, Model model){

//        model.addAttribute("post", markService.getMark(mid));

        return "user/map/markupdate";
    }
}

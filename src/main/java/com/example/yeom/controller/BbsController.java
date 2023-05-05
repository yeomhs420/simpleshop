package com.example.yeom.controller;

import com.example.yeom.dto.BbsDto;
import com.example.yeom.dto.CommentDto;
import com.example.yeom.entity.Bbs;
import com.example.yeom.entity.Comment;
import com.example.yeom.service.BbsService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BbsController {
    @Autowired
    BbsService bbsService;

    @Autowired
    private HttpSession session;

    @GetMapping("/bbs")
    public String Bbs(Model model,HttpServletRequest request){

        int pageNumber = 1;

        if(request.getParameter("pageNumber") != null)
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

        if(pageNumber != 1)
            model.addAttribute("prevExist", pageNumber);

        Page<Bbs> bbsList = bbsService.getBbsList(pageNumber);

        model.addAttribute("bbsList", bbsList);

        model.addAttribute("pageNumber", pageNumber);

        model.addAttribute("userID", session.getAttribute("userID"));

        return "board/bbs";
    }

    @GetMapping("/next_page")
    public String NextPage(HttpServletRequest request, RedirectAttributes attributes){

        int pageNumber;

        if(bbsService.getBbsList(Integer.parseInt(request.getParameter("pageNumber")) + 1).getContent().isEmpty()){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        else{
            pageNumber = Integer.parseInt(request.getParameter("pageNumber")) + 1;
        }


        attributes.addAttribute("pageNumber", pageNumber);

        return "redirect:/bbs";
    }

    @GetMapping("/prev_page")
    public String PrevPage(HttpServletRequest request, RedirectAttributes attributes){
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;

        attributes.addAttribute("pageNumber", pageNumber);

        return "redirect:/bbs";
    }

    @GetMapping("/write")
    public String Write(Model model){
        model.addAttribute("userID", session.getAttribute("userID"));
        return "board/write";
    }
    @RequestMapping("/writeAction")
    public String WriteAction(Model model, BbsDto bbsDto){
        bbsService.insertBbs(bbsDto, (String)session.getAttribute("userID"));

        return "redirect:/bbs";
    }

    @GetMapping("/bbs_view")
    public String BbsView(Model model, HttpServletRequest request){

        long bbsId = Long.parseLong(request.getParameter("bbs_id"));

        Bbs bbs = bbsService.getBbs(bbsId);

        List<Comment> comments = bbsService.getComments(bbsId);

        model.addAttribute("bbs", bbs);

        model.addAttribute("comments", comments);

        model.addAttribute("userID", session.getAttribute("userID"));

        return "board/bbs_view";
    }

    @RequestMapping("/commentAction")
    public String CommentAction(CommentDto commentDto, HttpServletRequest request){

        if(request.getParameter("userID") == null)
            return "redirect:/write";

        bbsService.insertComment(commentDto);

        return "redirect:/bbs_view?bbs_id=" + commentDto.getBbsId();
    }


    @RequestMapping("/bbs_update")
    public String BbsUpdate(@RequestParam Long bbsID, Model model){

        if(session.getAttribute("userID") == null) {
            model.addAttribute("msg", "로그인을 하세요");
            model.addAttribute("url", "/login");
            return "/board/bbs_update";
        }

        String userID = bbsService.getUserID(bbsID); // bbsID를 통한 userID 가져오기

        String sessionID = session.getAttribute("userID").toString();

        if(!sessionID.equals(userID)) {     //  사용자와 글쓴이가 일치하는지 확인하여 권한 부여
            model.addAttribute("msg", "권한이 없습니다");
            model.addAttribute("url", "/bbs");
            return "/board/bbs_update";
        }


        Bbs bbs = bbsService.getBbs(bbsID);

        model.addAttribute("bbs", bbs);

        return "/board/bbs_update";
    }

    @RequestMapping("/bbs_updateAction")
    public String BbsUpdateAction(HttpServletRequest request){

        bbsService.updateBbs(request);

        return "redirect:/bbs_view?bbs_id=" + request.getParameter("bbsID");
    }

    @RequestMapping("/bbs_delete")
    public String BbsDelete(@RequestParam Long bbsID, Model model){

        if(session.getAttribute("userID") == null) {
            model.addAttribute("msg", "로그인을 하세요");
            model.addAttribute("url", "/login");
            return "/board/bbs_update";
        }

        String userID = bbsService.getUserID(bbsID);

        String sessionID = session.getAttribute("userID").toString();

        System.out.println(userID + "," + sessionID + "," + bbsID);

        if(!sessionID.equals(userID)) {
            model.addAttribute("msg", "권한이 없습니다");
            model.addAttribute("url", "/bbs");
            return "/board/bbs_update";
        }



        bbsService.deleteBbs(bbsID);

        return "redirect:/bbs";
    }


}

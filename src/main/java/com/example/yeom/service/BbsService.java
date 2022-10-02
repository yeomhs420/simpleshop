package com.example.yeom.service;

import com.example.yeom.dto.BbsDto;
import com.example.yeom.dto.CommentDto;
import com.example.yeom.entity.Bbs;
import com.example.yeom.entity.Comment;
import com.example.yeom.entity.User;
import com.example.yeom.repository.BbsRepository;
import com.example.yeom.repository.CommentRepository;
import com.example.yeom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BbsService {
    @Autowired
    BbsRepository bbsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public void insertBbs(BbsDto bbsDto, String userID) {   // 게시판 글 등록
        User user = userRepository.findByUserId(userID).get(0);
        Long id = userRepository.findByUserId(userID).get(0).getId();

        Bbs bbs = new Bbs();

        bbs.setUser(user);
        bbs.setTitle(bbsDto.getTitle());
        bbs.setContent(bbsDto.getContent());

        bbsRepository.save(bbs);
    }

    public Bbs getBbs(Long id){
        Bbs bbs = bbsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않씁니다."));

        return bbs;
    }

    public Page<Bbs> getBbsList(int pageNumber){
        Page<Bbs> Bbs = bbsRepository.findAll(PageRequest.of(pageNumber - 1,10));

        return Bbs;
    }

    public List<Comment> getComments(Long id){
        return commentRepository.findByBbsId(id);
    }

    public void insertComment(CommentDto commentDto){   // 댓글 등록

        Bbs bbs = bbsRepository.findByBbsId(commentDto.getBbsId()).get(0);
        Comment comment = Comment.createComment(commentDto, bbs);

        commentRepository.save(comment); // 입력한 댓글 저장

    }

    public String getUserID(Long bbsID){return bbsRepository.findById(bbsID).get().getUser().getUserID();}

    public void updateBbs(HttpServletRequest request){  // 댓글 수정

        Long bbsID = Long.parseLong(request.getParameter("bbsID"));
        String title = request.getParameter("Title");
        String content = request.getParameter("Content");
        String userID = bbsRepository.findByBbsId(bbsID).get(0).getUser().getUserID();
        User user = userRepository.findByUserId(userID).get(0);

        Bbs bbs = bbsRepository.findById(bbsID).orElse(null);
        Bbs updatedBbs = new Bbs(bbsID,  title, user, content);

        if(bbs != null){
            bbsRepository.save(updatedBbs);    // 게시판 수정
        }
    }

    public void deleteBbs(long bbsID){  // 댓글 삭제
        Bbs bbs = bbsRepository.findById(bbsID).orElse(null);

        if(bbs != null){
            bbsRepository.delete(bbs);
        }
    }
}

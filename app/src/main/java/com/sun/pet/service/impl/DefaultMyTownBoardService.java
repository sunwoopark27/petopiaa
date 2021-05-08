package com.sun.pet.service.impl;

import java.util.List;
import com.sun.pet.dao.MyTownBoardDao;
import com.sun.pet.domain.MyTownBoard;
import com.sun.pet.service.MyTownBoardService;

public class DefaultMyTownBoardService implements MyTownBoardService {

  MyTownBoardDao boardDao; 

  public DefaultMyTownBoardService(MyTownBoardDao boardDao) {
    this.boardDao = boardDao;
  }

  // 게시글 등록 업무
  @Override
  public int add(MyTownBoard board) throws Exception {
    return boardDao.insert(board);
  }

  // 게시글 목록 조회 업무
  @Override
  public List<MyTownBoard> list() throws Exception {
    return boardDao.findByKeyword(null);
  }

  // 게시글 상세 조회 업무
  @Override
  public MyTownBoard get(int no) throws Exception {
    MyTownBoard board = boardDao.findByNo(no);
    if (board != null) {
      boardDao.updateViewCount(no);
    }
    return board; 
  }

  // 게시글 변경 업무
  @Override
  public int update(MyTownBoard board) throws Exception {
    return boardDao.update(board);
  }

  // 게시글 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return boardDao.delete(no);
  }

  // 게시글 검색 업무
  @Override
  public List<MyTownBoard> search(String keyword) throws Exception {
    return boardDao.findByKeyword(keyword);
  }
}








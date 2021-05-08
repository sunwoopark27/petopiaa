package com.sun.pet.dao;

import java.util.List;
import com.sun.pet.domain.MyTownBoard;

// BoardDao 의 규칙 정의
public interface MyTownBoardDao {

  int insert(MyTownBoard board) throws Exception;

  List<MyTownBoard> findByKeyword(String keyword) throws Exception;

  MyTownBoard findByNo(int no) throws Exception;

  int update(MyTownBoard board) throws Exception;

  int updateViewCount(int no) throws Exception;

  int delete(int no) throws Exception;
}













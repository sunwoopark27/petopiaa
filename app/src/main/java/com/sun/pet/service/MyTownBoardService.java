package com.sun.pet.service;

import java.util.List;
import com.sun.pet.domain.MyTownBoard;

public interface MyTownBoardService {

  int add(MyTownBoard board) throws Exception;

  List<MyTownBoard> list() throws Exception;

  MyTownBoard get(int no) throws Exception;

  int update(MyTownBoard board) throws Exception;

  int delete(int no) throws Exception;

  List<MyTownBoard> search(String keyword) throws Exception;
}








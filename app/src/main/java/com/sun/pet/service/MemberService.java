package com.sun.pet.service;

import com.sun.pet.domain.Member;

public interface MemberService {

  int add(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

}








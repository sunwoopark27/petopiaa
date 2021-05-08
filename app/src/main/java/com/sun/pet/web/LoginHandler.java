package com.sun.pet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.pet.domain.Member;
import com.sun.pet.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/login")
public class LoginHandler extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String password = request.getParameter("password");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");

    try {
      Member member = memberService.get(id, password);
      out.println("</head>");
      out.println("<body>");
      if (member == null) {
        request.getSession().invalidate();
        throw new ServletException("사용자 정보가 맞지 않습니다.");
      }

      request.getSession().setAttribute("loginUser", member);

      out.printf("<p>%s 님 로그인하였습니다.</p>\n", member.getId());

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>로그인 실패</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
    } 

    out.println("</body>");
    out.println("</html>");
  }
}

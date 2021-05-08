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
import com.sun.pet.domain.MyTownBoard;
import com.sun.pet.service.MyTownBoardService;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class MyTownBoardUpdateHandler extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MyTownBoardService boardService = (MyTownBoardService) request.getServletContext().getAttribute("boardService");
    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시글 변경</title>");

    try {
      request.setCharacterEncoding("UTF-8");
      int no = Integer.parseInt(request.getParameter("no"));

      MyTownBoard oldBoard = boardService.get(no);
      if (oldBoard == null) {
        throw new Exception ("해당 번호의 게시글이 없습니다.");
      }
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("변경 권한이 없습니다!");
      }

      MyTownBoard board = new MyTownBoard();
      board.setNo(oldBoard.getNo());
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      boardService.update(board);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 변경</h1>");
      out.println("<p>게시글을 변경하였습니다.</p>");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 변경 오류</h1>");
      out.printf("<p>%s</p>\n",e.getMessage());
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<a href='list'>목록</a></p>\n");
    }

    out.println("</body>");
    out.println("</html>");
  }
}







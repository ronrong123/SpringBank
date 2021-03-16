package com.company.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.board.service.FboardVO;
import com.company.board.service.impl.BoardMapper;
import com.company.common.FileRenamePolicy;

@Controller
public class FboardController {

	@Autowired
	BoardMapper boardmapper;

	@GetMapping("/insertFboard")
	public String insertFboardForm() {
		return "board/insertBoard";
	}

	@PostMapping("/insertFboard")
	public String insertFboard(FboardVO vo) throws IllegalStateException, IOException {
		System.out.println(vo);
		// 첨부파일처리
		MultipartFile[] files = vo.getUploadFile();
		String filenames ="";
		boolean start = true;
		for(MultipartFile file : files) {
			if (file != null && !file.isEmpty() && file.getSize() > 0) {
				String filename = file.getOriginalFilename();
				//파일명 중복체크 -> rename
				File rename = FileRenamePolicy.rename(new File("c:/upload", filename));
				// 업로드된 파일명
				//rename.getName()
					if(!start) {
						filenames += ",";						
					}else {
						start = false;
					}
					filenames += rename.getName();
				//파일명을 읽어내는게 getName()
				//임시폴더에서 업로드 폴더로 파일이동
				file.transferTo(rename); // transferTo:이동한다는뜻 괄호안에 업로드 위치를 정함)
			}
		}
		vo.setFilename(filenames); // vo에 저장 vo 업로드된 파일명을 담아서 DB에 저장(파일이름만 저장)
		// 등록 서비스 호출
		boardmapper.insertFboard(vo);
		return "redirect:/getBoard?seq=" + vo.getSeq();
	}

	// 단건조회
	@GetMapping("/getBoard")
	public String getBoard(FboardVO vo, Model model) {
		model.addAttribute("board", boardmapper.getBoard(vo));
		return "board/getBoard";
	}

	// 파일다운
	@GetMapping("/fileDown")
	public void fileDown(FboardVO vo, HttpServletResponse response) throws IOException {
		vo = boardmapper.getBoard(vo);
		File file = new File("c:/upload", vo.getFilename());
		if (file.exists()) { //file이 있으면
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");

			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(response.getOutputStream());
				// 파일을 inputStream 으로 읽어서 outputstream으로 복사(다운로드)
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}

		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")
								.append("alert('file not found(파일없음)');")
								.append("history.go(-1);")
								.append("</script>");
		}
	}
	
	// 여러개파일다운
	@GetMapping("/fileNameDown")
	public void fileNameDown(FboardVO vo, HttpServletResponse response) throws IOException {
		File file = new File("c:/upload", vo.getFilename());
		if (file.exists()) { //file이 있으면
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");

			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(response.getOutputStream());
				// 파일을 inputStream 으로 읽어서 outputstream으로 복사(다운로드)
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}

		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")
								.append("alert('file not found(파일없음)');")
								.append("history.go(-1);")
								.append("</script>");
		}
	}
}

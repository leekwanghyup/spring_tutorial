package me.light.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import me.light.model.Board;
import me.light.validation.BoardValidator;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@GetMapping("list")
	public String list() {
		return "board/list";
	}
	
	
	@GetMapping("/register")
	public String registerForm(@ModelAttribute("boardVo") Board board) {
		return "board/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("boardVo") Board board, Errors errors) {
		new BoardValidator().validate(board, errors);
		if(errors.hasErrors()) {
			return "board/register";
		}
		System.out.println("제목:"+board.getTitle());
		System.out.println("내용:"+board.getContent());
		System.out.println("작성자:"+board.getWriter());
		return "redirect:list";
	}
}

package com.ssafy.vue.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/memo")
@Api("게시판 컨트롤러  API V1")
public class MemoController {

	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemoService MemoService;

	@GetMapping(value="/{articleNo}")
	public ResponseEntity<?> getMemoList(@PathVariable("articleNo") String articleno) throws IOException {
		logger.info("getMemoList - 호출");
		System.out.println(articleno);
		int articleNo = Integer.parseInt(articleno);
		List<MemoDto> list = null;
		try {
			list = MemoService.getMemoList(articleNo);
			for (MemoDto memoDto : list) {
				System.out.println(memoDto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemoDto>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/")
	public ResponseEntity<?> addMemo(@RequestBody MemoDto memo) throws IOException {
		System.out.println(memo);
		List<MemoDto> list = null;
		try {
			MemoService.addMemo(memo);
			System.out.println("getmemolist");
			list = MemoService.getMemoList(memo.getArticleNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemoDto>>(list,HttpStatus.OK);
		
	}
	

	@DeleteMapping(value="/{articleno}/{memono}")
	public ResponseEntity<?> deleteMemo(@PathVariable("memono") int memono, @PathVariable("articleno") int articleno) throws IOException {
		logger.info("getMemoList - 호출");
		List<MemoDto> list = null;
		try {
			MemoService.removeMemo(memono);
			System.out.println("delete memo");
			list = MemoService.getMemoList(articleno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemoDto>>(list,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/{articleno}")
	public ResponseEntity<?> deleteMemo(@PathVariable("articleno") int articleno) throws IOException {
		logger.info("getMemoList - 호출");
		List<MemoDto> list = null;
		try {
			MemoService.removeAllMemo(articleno);
			System.out.println("delete memo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping(value="/")
	public ResponseEntity<?> updateMemo(@RequestBody MemoDto memo) throws IOException {
		logger.info("updatememo - 호출");
		List<MemoDto> list = null;
		System.out.println(memo);
		try {
			MemoService.updateMemo(memo);
			list = MemoService.getMemoList(memo.getArticleNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemoDto>>(list,HttpStatus.OK);
		
	}
}
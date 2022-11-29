package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.FileInfoDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemoMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
		int res = sqlSession.getMapper(BoardMapper.class).writeArticle(boardDto);
		if (fileInfos != null && !fileInfos.isEmpty()) {
			sqlSession.getMapper(BoardMapper.class).registerFile(boardDto);
		}
		return res == 1;
	}

	@Override
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		List<BoardDto> list = sqlSession.getMapper(BoardMapper.class).listArticle(boardParameterDto);
		for (BoardDto b : list) {
			List<FileInfoDto> files = sqlSession.getMapper(BoardMapper.class).fileInfoList(b.getArticleno());
			b.setFileInfos(files);
			
		}
		return list;
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		List<FileInfoDto> list = sqlSession.getMapper(BoardMapper.class).fileInfoList(articleno);
		BoardDto board = sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
		board.setFileInfos(list);
		return board;
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteMemo(articleno);
		sqlSession.getMapper(BoardMapper.class).deleteFileinfo(articleno);
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public boolean deleteArticleByUser(String userid) throws Exception {
		BoardParameterDto b = new BoardParameterDto();
		b.setWord("");
		List<BoardDto> list = sqlSession.getMapper(BoardMapper.class).listArticle(b);
		for (BoardDto boardDto : list) {
			if(boardDto.getUserid().equals(userid)) {
				sqlSession.getMapper(MemoMapper.class).removeAllMemo(boardDto.getArticleno());
				sqlSession.getMapper(BoardMapper.class).deleteFileinfo(boardDto.getArticleno());
				int v = sqlSession.getMapper(BoardMapper.class).deleteArticle(boardDto.getArticleno());
				if(v != 1)
					return false;
			}
		}
		
		return true;
	}
}
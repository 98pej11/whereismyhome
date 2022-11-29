package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemoMapper;


@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MemoDto> getMemoList(int articleNo) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).getMemoList(articleNo);
	}

	@Override
	public void addMemo(MemoDto memo) throws Exception {
		sqlSession.getMapper(MemoMapper.class).addMemo(memo);
	}

	@Override
	public void removeMemo(int memono) throws Exception {
		sqlSession.getMapper(MemoMapper.class).removeMemo(memono);
	}

	@Override
	public void updateMemo(MemoDto memo) throws Exception {
		sqlSession.getMapper(MemoMapper.class).updateMemo(memo);
	}

	@Override
	public void removeAllMemo(int articleno) throws Exception {
		sqlSession.getMapper(MemoMapper.class).removeAllMemo(articleno);
	}

}

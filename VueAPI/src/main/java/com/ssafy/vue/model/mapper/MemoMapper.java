package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;

@Mapper
public interface MemoMapper {

	List<MemoDto> getMemoList(int articleNo) throws Exception;
	void addMemo(MemoDto memo) throws Exception;
	void removeMemo(int memono) throws Exception;
	void updateMemo(MemoDto memo) throws Exception;
	void removeAllMemo(int articleno) throws Exception;
	
}
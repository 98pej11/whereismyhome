package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemoDto;

public interface MemoService {

	List<MemoDto> getMemoList(int articleNo) throws Exception;
	void addMemo(MemoDto memo) throws Exception;
	void removeMemo(int memono) throws Exception;
	void updateMemo(MemoDto memo) throws Exception;
	void removeAllMemo(int articleno) throws Exception;
}

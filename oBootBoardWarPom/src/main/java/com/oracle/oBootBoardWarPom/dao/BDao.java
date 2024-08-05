package com.oracle.oBootBoardWarPom.dao;

import java.util.ArrayList;

import com.oracle.oBootBoardWarPom.dto.BDto;



public interface BDao {
	
	public ArrayList<BDto> boardList();
	public BDto contentView(String strId);
	public int modifyBoard(BDto board);
	public int deleteBoard(int bId);
	public void writeBoard(BDto board);
	public BDto reply_view(String bId);
	public void replyBoard(BDto board);

}

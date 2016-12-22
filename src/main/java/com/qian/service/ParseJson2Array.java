package com.qian.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class ParseJson2Array {
	/**
	 * json 串解析成棋盘数组
	 * 
	 * @param json
	 * @return
	 */
	public static int[][] parse(String json) {
		JSONArray row = JSON.parseArray(json);
		int rowCnt = row.size();
		JSONArray jsonArray = row.getJSONArray(0);
		int colCnt = row.getJSONArray(0).size();
		int[][] chessBoard = new int[rowCnt][colCnt];
		for (int i = 0; i < rowCnt; i++) {
			jsonArray = row.getJSONArray(i);
			for (int j = 0; j < colCnt; j++) {
				chessBoard[i][j] = jsonArray.getIntValue(j);
			}
		}
		return chessBoard;
	}
}

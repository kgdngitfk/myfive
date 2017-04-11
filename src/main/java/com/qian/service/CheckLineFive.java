package com.qian.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wuhuaiqian
 * @date 2016年12月22日
 */
public class CheckLineFive {
	final Logger logger  =  LoggerFactory.getLogger(CheckLineFive.class );
	// 棋盘落子信息
	private int[][] chessboard;

	public CheckLineFive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckLineFive(int[][] chessboard) {
		super();
		this.chessboard = chessboard;
	}

	/**
	 * 判断在（i，j）处是否有who的值连成五
	 * 
	 * @param i
	 * @param j
	 * @param who
	 * @return
	 */
	public boolean fiveAt(int i, int j, int who) {
		boolean checkHorizonal = this.checkHorizonal(i, j, who);
		boolean checkVertical = this.checkVertical(i, j, who);
		boolean checkSlash = this.checkSlash(i, j, who);
		boolean checkBackslash = this.checkBackslash(i, j, who);
		return checkHorizonal || checkVertical || checkSlash || checkBackslash;
	}

	private boolean checkHorizonal(int i, int j, int who) {
		do {
			if (chessboard[i][j] == who)
				i--;
			else
				break;
		} while (i >= 0);
		// 找到最左边的子
		i++;
		for (int index = 0; i <= chessboard.length && index < 5; index++) {
			if (chessboard[i++][j] != who) {
				logger.info("横向没有成五");
				return false;
			}
		}
		return true;
	}

	private boolean checkVertical(int i, int j, int who) {
		// 竖
		// 找到最上面的子
		do {
			if (chessboard[i][j] == who)
				j--;
			else
				break;
		} while (j >= 0);
		j++;
		for (int index = 0; j <= chessboard.length && index < 5; index++) {
			if (chessboard[i][j++] != who) {
				logger.info("垂直向没有成五");
				return false;
			}
		}
		return true;
	}

	private boolean checkBackslash(int i, int j, int who) {
		// /方向 反斜线
		// 找到最右上角的子
		do {
			if (chessboard[i][j] == who) {
				i++;
				j--;// 游标向右上方移动
			} else
				break;

		} while (i <= chessboard.length && j >= 0);
		i--;
		j++;
		int cnt = 0;
		for (int index = 0; index < 5 && i >= 0 && j <= chessboard.length; index++) {
			if (chessboard[i--][j++] != who) {
				logger.info("反斜杠向没有成五");
				return false;
			}
			cnt++;
		}
		return cnt == 5;
	}

	// \方向
	private boolean checkSlash(int i, int j, int who) {
		// 找到最左上角的子
		do {
			if (chessboard[i][j] == who) {
				i--;
				j--;
			} else
				break;
		} while (i >= 0 && j >= 0);
		i++;
		j++;
		int cnt = 0;
		for (int index = 0; i <= chessboard.length && j <= chessboard.length && index < 5; index++) {
			if (chessboard[i++][j++] != who) {
				logger.info("正斜杠向没有成五");
				return false;
			}
			cnt++;
		}
		return cnt == 5;
	}
}

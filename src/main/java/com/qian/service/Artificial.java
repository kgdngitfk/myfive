package com.qian.service;

import com.qian.entity.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuhuaiqian on 17-3-12.
 * 根据慕课网中JS AI五子棋教程的实现
 * 赢法数组 ：记录了五子棋所有的赢法 三维数组
 * 每种赢法的统计数组  一维数组
 */
public class Artificial {
    final Logger logger  =  LoggerFactory.getLogger(Artificial.class );
    //默认五子连线
    private static final int DEFAULT_COUNT = 5;
    // 当前棋盘落子信息
    private int[][] chessboard;
    //所有的可能连成5子的情况
    private int [][][] wins ;
    //赢法统计数组，长度等于所有可能的赢法，win[k]的值表示我方在第k中赢上已经有多少个子了
    private int win [];
    private int count =0;
    {


    }

    public Artificial(int[][] chessboard) {
        init(chessboard);
    }

    private void init(int[][] chessboard) {
        this.chessboard = chessboard;
        wins = new int[chessboard.length][chessboard[0].length][5];
        //
        int count =0;
        //所有连成五子的时候是垂直的情况
        // wins[0][0][0] =1
        // wins[0][1][0] =1
        // wins[0][2][0] =1
        // wins[0][3][0] =1
        // wins[0][4][0] =1
        //第零种赢法在棋盘上（0,0）（0,1）（0,2）（0,3）（0,4）处有子；下同，
        // wins[0][1][1] =1
        // wins[0][2][1] =1
        // wins[0][3][1] =1
        // wins[0][4][1] =1
        // wins[0][5][1] =1
        for(int i=0;i<wins.length;i++){
            for (int j=0;j<wins[0].length;j++){
                for(int k=0;k<DEFAULT_COUNT;k++){
                    if((j+k)<wins[0].length){
                        wins[i][j+k][0] =count;
                    }
                    else break;
                }
                count++;
            }
        }
        //所有的水平的情况
        for(int i=0;i<wins.length;i++){
            for (int j=0;j<wins[0].length;j++){
                for(int k=0;k<DEFAULT_COUNT;k++){
                    if((j+k)<wins.length){

                        wins[j+k][i][0] =count;
                    }
                    else break;
                }
                count++;
            }
        }
        //所有的\方向
        for(int i=0;i<wins.length;i++){
            for (int j=0;j<wins[0].length;j++){
                for(int k=0;k<DEFAULT_COUNT;k++){
                    if((i+k)<wins.length&&(j+k)<wins[0].length){
                        wins[i+k][j+k][0] =count;
                    }
                    else break;
                }
                count++;
            }
        }
        //所有的/方向
        for(int i=0;i<wins.length;i++){
            for (int j=0;j<wins[0].length;j++){
                for(int k=0;k<DEFAULT_COUNT;k++){
                    if((i+k)<wins.length&&(j-k)>0){
                        wins[i+k][j-k][0] =count;
                    }
                    else break;
                }
                count++;
            }
        }
        this.win = new int[count];
        //15x15教程中说的是572种
        logger.debug("{}x{}棋盘上{}连珠共有{}种赢法",chessboard.length,chessboard[0].length,DEFAULT_COUNT,count);

    }

    /**
     * 根据当前棋盘落子情况为who 给出一个最优解
     * @param who
     * @return
     */
    public Step answer(int who){

        return new Step();
    }



}

/**
 * 
 */
	var ws = connect(callback);
	var chessboard = new Array ();
	var who =true;// 谁的轮次
	var win =false;// 是否胜利
	var lineNumber = 19;
	var me =1;// 表示该位置是我方棋子
	var opponent =2;// 该位置是对方棋子
	var chessSize;// 棋子半径
	var boundary ;// 棋盘边界距离
	var cellSize;// 格子大小
	var dom = document.getElementById("borad");
	var info =document.getElementById("info");
	var ctx = dom.getContext("2d");//
	var width = ctx.canvas.width;
	var height = ctx.canvas.height;
	cellSize = width / 20;// 棋盘格子大小
	boundary = width /20;
	chessSize = cellSize/2;
	function callback(data) {
		alert(data);
		console.log(data);
    }
	function init() {
		// 初始化棋盘位置
		for(var i=0;i<20;i++){
			chessboard[i] = new Array();
			for(var k=0;k<20;k++)
			chessboard[i][k]=-1;// 无子为-1
		}
		/*
		 * var cnt=0; for debug for(var i=0;i<20;i++){ for(var j=0;j<20;j++){
		 * cnt++; console.log(chessboard[i][j]); } } console.log(cnt);
		 */
		// 画棋盘边框
		ctx.beginPath();// 开始一次绘制
		ctx.rect(0,0,width,height);
		ctx.fillStyle="#AAAAAA";// 背景色
		ctx.lineWidth =8;
		console.log("current line width is "+ctx.lineWidth);
		ctx.fill();
		// 以网格线的左上为坐标原点
		ctx.translate(boundary,boundary);
		// ctx.arc(0,0,chessSize,0,2*Math.PI);
		// ctx.fill();
		// 画线条
		ctx.beginPath();
		ctx.lineWidth = 2;
		console.log("current line width is "+ctx.lineWidth);
		// 风格线,用image代替更好,从哪找美工0.0
		// 竖线
		 for (var i = 0; i < lineNumber; i++) {
			ctx.moveTo(i * cellSize,0);
			ctx.lineTo(i * cellSize, height-2*boundary);
		} 
		// 横线
		for (var j = 0; j < lineNumber; j++) {
			ctx.moveTo(0, j * cellSize);
			ctx.lineTo(width-2*boundary, j * cellSize);
		}
		ctx.stroke();
	}
	/* 落子 */
	function putOne(event) {
		console.log("is my turn ?"+who);
		if(who){// 本方轮次
			/*
			 * 点击事件的坐标原点在是canvas画布的左上角 , translate()后画面的原点在网格线的左上角
			 */
			var x = event.offsetX-boundary;
			var y = event.offsetY-boundary
			if(x>0&&x<450&&y>0&&y<450){
				console.log('点击点相对网格原点坐标'+'('+x+','+y+')')
				/*
				 * 将鼠标点击点相对canvas原点的坐标值拿到, 并找到离它最近的风格线的交点坐标
				 */
				var i = Math.ceil(x / cellSize-0.5);// 数组中的横坐标
				var j = Math.ceil(y / cellSize-0.5);// 数组中的纵坐标
				console.log('('+i+','+j+')');
				if(chessboard[i][j]!=-1){// 有子
					alert("can't put chess here");
				}
				else{
					var msg = {"myId":me,"opponentId":opponent,"type":"step","x":i,"y":j};
					var json = JSON.stringify(msg);
					console.log('msg'+msg);
					//console.log(JSON.stringify(chessboard));
					/* {"myId":0,"opponentId":0,"type":"step","x":3,"y":2} */
					ws.send(json);
					// chessboard [i][j] =me;//在数据中做记录
					who = false;// 对方轮次
					drawChess(i,j,me);
					if(line5(i,j)){
						alert("you are win");
					}
				}
				
			}
			else{
				alert("放棋盘外面了");
			}
			
		}
		else{
			alert("请等待对方落子");
		}
		
	}
	/*
	 * 画棋子 @ param i 数组中横坐标 @ param j 数组中的纵坐标
	 */
	function drawChess(i,j,which){
		chessboard [i][j] =which;// 在数据中做记录
		var x =i*cellSize;
		var y =j*cellSize;
		console.log('draw chess in('+x+','+y+')');
		console.log('record in chessboard['+i+']'+'['+j+']');
		ctx.beginPath();
		if(which==me){
			console.log('----'+which);
			ctx.fillStyle="#FFFFFF";// 我方棋子颜色
		}
		else{
			ctx.fillStyle="#000000";
		}
		ctx.arc(x,y,chessSize,0,2*Math.PI);
		ctx.fill();
		// who=!who;//转换轮次
	}
	/*
	 * 判断落点处是否构成五子连珠 @param i 落子点横索引 @param j 落子点纵索引
	 */
	 function line5(i,j){
		var x = i;
		var y = j;
		var cnt=0;
		// 在棋盘内
		var flag1 = checkHorizonal(i,j);
		var flag2 = checkVertical(i,j);
		var flag3 = checkBackslash(i,j);
		var flag4 = checkSlash(i,j);
		/*
		 * console.log('flag1'+flag1); console.log('flag2'+flag2);
		 * console.log('flag3'+flag3); console.log('flag4'+flag4);
		 */
		var flag = flag1||flag2||flag3||flag4;
		console.log('flag'+flag);
		return flag
	}
	// 水平方向
	function checkHorizonal(i,j){
		var cnt =0;
		do{
			if(chessboard[i][j]==me)
				i--;
			else
				break;
		}while(i>=0)
		// 找到最左边的子
		i++;
		console.log('最左边的位置在'+i);
		for(var index =0;i<=lineNumber&&index<5;index++){
			if(chessboard[i++][j]!=me){
				// console.log("横向没有成五");
				return false;
			}
		}
		console.log("横向成五");
		return true;
		
	}
	// 竖
	function checkVertical(i,j){
		// 找到最上面的子
		do{
			if(chessboard[i][j]==me)
				j--;
			else
				break;
		}while(j>=0)
		j++;
		console.log('最上边的位置在'+j);
		for(var index =0;j<=lineNumber&&index<5;index++){
			if(chessboard[i][j++]!=me){
				// console.log("垂直向没有成五");
				return false;
			}
		}
		console.log("垂直向成五");
		return true;
	}
	// /方向 反斜线
	function checkBackslash(i,j){
		// 找到最右上角的子
		do{
			if(chessboard[i][j]==me){
				i++;
				j--;// 游标向右上方移动
			}
			else
				break;
			
		}while(i<=lineNumber&&j>=0)
		i--;
		j++;
		var cnt =0;
		for(var index =0;index<5&&i>=0&&j<=lineNumber;index++){
			if(chessboard[i--][j++]!=me){
				// console.log("反斜杠向没有成五");
				return false;
			}
			cnt++;
		}
		console.log("反斜杠向成五");
		return cnt==5;
	}
	// \方向
	function checkSlash(i,j){
		// 找到最左上角的子
		do{
			if(chessboard[i][j]==me){
				i--;
				j--;
			}
			else
				break;
		}while(i>=0&&j>=0)
		i++;
		j++;
		var cnt =0;
		for(var index =0;i<=lineNumber&&j<=lineNumber&&index<5;index++){
			if(chessboard[i++][j++]!=me){
				// console.log("正斜杠向没有成五");
				return false;
			}
			cnt++;
		}
		console.log("正斜杠向成五");
		return cnt==5;
	}
	// 设置自己和对手的落子代表的值
	function setVar(msg){
		me = msg.myId;
		console.log(me);
		opponent = msg.opponentId;
		console.log(opponent);
	}
	function info(info){
		
	}
	init();
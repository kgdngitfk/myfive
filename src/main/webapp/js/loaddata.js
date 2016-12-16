/**
 * 用于与服务器通讯的websocket
 */
function connect() {
	var ws;
	var lastMsg;
	window.WebSocket = window.WebSocket || window.MozWebSocket;
	if (!window.WebSocket) { // 检测浏览器支持
		console.log('Error: WebSocket is not supported .');
		alert(" no support websocket");
	}
	if (ws == null) {
		var host = window.location.host;
		console.log(host);
		var pathName = window.location.pathname.substring(1);
		console.log(window.location.pathname);
		var webName = pathName == '' ? '' : pathName.substring(0, pathName
				.indexOf('/'));
		console.log(webName);
		/*拼成websocket的请求地址，因为webpath名称可能会发生变化，所以要动态获取*/
		/* 写死的形式：ws = new WebSocket("ws://localhost:8080/WebSocket/five"); */
		ws = new WebSocket('ws://' + host + '/'+webName+'/five');
	}
	ws.onopen = function(evn) {
		console.log(evn);
		lasgMsg = evn.data;
	};
	ws.onmessage = function(evn) {
		console.log(evn.data);
		lastMsg = evn.data;
		console.log(lastMsg);
		var msg = JSON.parse(lastMsg);
		console.log('msg.match' + msg.type);
		if (msg.type == "match")
			setVar(msg);
		else {
			//感觉这里设计得不好，整个项目的模块化功能不清晰，不利于后续的扩展
			drawChess(msg.x, msg.y, msg.myId);
			who = true;
		}
	};
	ws.onclose = function() {
		console.log("server close the connection");
		ws.close();
		ws = null;
	};
	return ws;
};
function send(msg) {
	ws.send(msg);
}
function c() {
	alert("close");
	ws.close();
}

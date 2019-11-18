package com.example.slackbot.controller;

import java.io.IOException;
import java.net.Proxy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

@Controller
public class SlackBotConrtoller {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@PostMapping("/postMessage")
	public String postMessage(Model model, @RequestParam("message") String message) {

		SlackSession session;
		// プロキシのURL
		String proxyURL = Constants.PROXY_HOST;
		// プロキシのポート
		int proxyPort = Integer.parseInt(Constants.PROXY_PORT);
		// SlackのToken
		String slackToken = Constants.BOT_TOKEN;
		try {
			if(Constants.IS_USE_PROXY) {
				// プロキシを使っての通信
				session = SlackSessionFactory.getSlackSessionBuilder(slackToken).withProxy(Proxy.Type.HTTP, proxyURL, proxyPort).build();
			}else {
				// プロキシなしでの通信
				session = SlackSessionFactory.createWebSocketSlackSession(Constants.BOT_TOKEN);
			}
			// セッションスタート
			session.connect();
			// チャンネル指定(IDで指定)
			SlackChannel channel = session.findChannelById(Constants.CHANNEL_ID);
			// POSTで受け取ったメッセージを送信
			session.sendMessage(channel, message);
			// セッション切断
			session.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}

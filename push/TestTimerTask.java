package com.ustb.push;

import java.util.TimerTask;

import net.sf.json.JSONObject;

public class TestTimerTask extends TimerTask {
	MqttBroker mqttBroker;
	public void setMqttBroker(MqttBroker mqttBroker) {
		this.mqttBroker = mqttBroker;
	}
	int flag = 0;
	@Override
	public void run() {
		JSONObject json = new JSONObject();
		if(flag == 0){
			json.put("type", "phone");
			json.put("message", "已YY人抢购，数量有限，速来！");
			mqttBroker.sendMessage("sly12", json.toString());
			flag = 1;
		}
		else{
			json.put("type", "clothe");
			json.put("message", "已YY人抢购，数量有限，速来！");
			mqttBroker.sendMessage("sly12", json.toString());
			flag =0;
		}
	
	}
}


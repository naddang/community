package com.dev_cbj.community.config;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

/**
 * MQTT 설정
 */
@Configuration
public class MqttConfig {
	public final String BROKER_URL; // MQTT 브로커 주소
	private final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId(); // 클라이언트 ID
	private final String TOPIC_FILTER; // 구독토픽
	private final String MQTT_USERNAME; // 접근을 위한 ID
	private final String MQTT_PASSWORD; // 접근을 위한 비밀번호
	
//	private final ProjectSetting setting; // 임시로 DB와 연결하기위해 구현
	
	public MqttConfig(@Value("${mqtt.server.url}") String brokerUrl, @Value("${mqtt.server.topic}") String topicFilter,
	                  @Value("${mqtt.server.username}") String userName, @Value("${mqtt.server.password}") String password) {
		this.BROKER_URL = brokerUrl;
		this.TOPIC_FILTER = topicFilter;
		this.MQTT_USERNAME = userName;
		this.MQTT_PASSWORD = password;
	}
	
	
	//MQTT 설정 세팅
	@Bean
	public DefaultMqttPahoClientFactory defaultMqttPahoClientFactory() {
		DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
		clientFactory.setConnectionOptions(connectOptions());
		return clientFactory;
	}
	
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
	@Bean
	public MessageChannel mqttOutputChannel() {
		return new DirectChannel();
	}
	
	
	/**
	 * MQTT 메시지를 수신하기 위한 채널
	 * @return MessageProducer
	 */
	@Bean
	public MessageProducer inboundChannel() {
		MqttPahoMessageDrivenChannelAdapter adapter =
				new MqttPahoMessageDrivenChannelAdapter(BROKER_URL, MQTT_CLIENT_ID, TOPIC_FILTER);
		
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}
	
	/**
	 * MQTT 메시지를 수신하기 위한 핸들러
	 * 수신 후 insert하고, 성공 시 소켓으로 데이터를 뿌려줌
	 * @return MessageHandler
	 */
	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler inboundMessageHandler() {
		
		return message -> {

		};
	}
	
	@Bean
	@ServiceActivator(inputChannel = "mqttOutputChannel")
	public MessageHandler mqttOutbound(DefaultMqttPahoClientFactory clientFactory) {
		MqttPahoMessageHandler messageHandler =
				new MqttPahoMessageHandler(MQTT_CLIENT_ID, clientFactory);
		messageHandler.setAsync(true);
		messageHandler.setDefaultQos(1);
		return messageHandler;
	}
	
	@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
	public interface OutboundGateway {
		void sendToMqtt(String payload, @Header(MqttHeaders.TOPIC) String topic);
	}
	
	
	private MqttConnectOptions connectOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setUserName(MQTT_USERNAME);
		options.setPassword(MQTT_PASSWORD.toCharArray());
		return options;
	}
}
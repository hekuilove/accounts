//package org.quinn.accounts.util.activemq;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.DeliveryMode;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class SendMessage {
//
//	public static void main(String[] args) {
//		ConnectionFactory factory; //连接工厂
//		Connection connection;//jms连接
//		Session session;//发送、接收线程
//		Destination destination;//消息目的地
//		MessageProducer producer;//消息发送者
//
//		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
//				"tcp://localhost:61616");
//
//		try {
//			connection = factory.createConnection();
//			connection.start();
//			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//			destination = session.createQueue("FirstQueue");
//			producer = session.createProducer(destination);
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  //是否为持久化 ，NON_PERSISTENT非持久化 ，PERSISTENT持久化
//
//			for (int i = 11; i < 13; i++) {
//				String msg = "第" + i + "次发送消息";
//
//				TextMessage textMessage = session.createTextMessage(msg);
//				producer.send(textMessage);
//			}
//			session.commit();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//
//	}
//}

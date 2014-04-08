package org.quinn.accounts.util.activemq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SendBytesMessage {

	public static void main(String[] args) {
		ConnectionFactory factory; //连接工厂
		Connection connection;//jms连接
		Session session;//发送、接收线程
		Destination destination;//消息目的地
		MessageProducer producer;//消息发送者

		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://localhost:61616");

		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			BytesMessage bytesMessage = session.createBytesMessage();

			File fil = new File("C:\\Users\\WS-SH-L1051\\Desktop\\a.txt");

			InputStream is = new FileInputStream(fil);

			byte by[] = new byte[is.available()];

			is.read(by);
			
			bytesMessage.writeBytes(by);

			producer.send(destination, bytesMessage);
			
			is.close();
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

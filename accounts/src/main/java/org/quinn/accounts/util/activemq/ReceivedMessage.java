package org.quinn.accounts.util.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;

public class ReceivedMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ConnectionFactory factory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageConsumer consumer;
		factory = new org.apache.activemq.ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			while (true) {
				TextMessage textMessage = (TextMessage) consumer.receive();
				if (textMessage != null){
					System.out.println(textMessage.getText());
					textMessage.acknowledge();
				}
				Thread.sleep(1000);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}

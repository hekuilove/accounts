//package org.quinn.accounts.util.activemq;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.Session;
//
//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.command.ActiveMQBytesMessage;
//
//public class ReceivedBytesMessage {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		ConnectionFactory factory;
//		Connection connection = null;
//		Session session;
//		Destination destination;
//		MessageConsumer consumer;
//		factory = new org.apache.activemq.ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
//				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
//		try {
//			connection = factory.createConnection();
//			connection.start();
//			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			destination = session.createQueue("FirstQueue");
//			consumer = session.createConsumer(destination);
//			while (true) {
//				Message msg = consumer.receive();
//				if (msg instanceof ActiveMQBytesMessage) {
//					ActiveMQBytesMessage bytesMessage = (ActiveMQBytesMessage) msg;
//					if (bytesMessage != null) {
//						byte[] bt = new byte[(int) bytesMessage.getBodyLength()];
//						bytesMessage.readBytes(bt);
//						File fil = new File("C:\\Users\\WS-SH-L1051\\Desktop\\b.txt");
//						if (!fil.exists()) {
//							fil.createNewFile();
//						}
//						OutputStream os = new FileOutputStream(fil);
//						os.write(bt);
//						os.close();
//					}
//				}
//				Thread.sleep(1000);
//			}
//		} catch (JMSException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//}

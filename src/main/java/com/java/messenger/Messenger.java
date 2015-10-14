package com.java.messenger;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import com.java.pojo.Block;

public class Messenger {
	private String topicName = "dynamicTopics/testT";
	private TopicConnection conn = null;
	private TopicSession session = null;
	private TopicPublisher publisher = null;
	private TopicSubscriber subscriber = null;
	private InitialContext context = null;
	private TopicConnectionFactory factory = null;
	private Topic topic = null;
	private MyMarshaller marshaller;
	
	public Messenger() throws NamingException, JMSException, JAXBException {
		this.context = new InitialContext();
		this.factory = (TopicConnectionFactory) this.context.lookup("TopicConnectionFactory");
		this.topic = (Topic) this.context.lookup(topicName);
		this.conn = this.factory.createTopicConnection();
		this.session = this.conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		this.publisher = this.session.createPublisher(this.topic);
		this.subscriber = this.session.createSubscriber(this.topic);
		this.marshaller = new MyMarshaller();
	}
	
	public synchronized void send(Block block) throws JAXBException, JMSException {
		// publish
		System.out.println("Inside of send function");
		System.out.println(block);
		this.marshaller.marshalObject(block);
		ObjectMessage message = this.session.createObjectMessage();
		this.conn.start();
		this.publisher.publish(message);
		System.out.println("Block message has been sent");
		System.out.println(this.marshaller.unmarshalObject((File)message.getObject()));
	}
	
	public Queue<Block> receive() throws JMSException, JAXBException {
		System.out.println("Inside of receive function");
		ObjectMessage objMessage = null;
		Message message = null;
		File temp = null;
		Block block = null;
		Queue<Block> blockQueue = new LinkedBlockingQueue<>();
		this.conn.start();
		int count = 0;
		while ((message = this.subscriber.receive(2000)) != null) {
			count++;
			objMessage = (ObjectMessage) message;
			temp = (File)objMessage.getObject();
			block = this.marshaller.unmarshalObject(temp);
			System.out.println(block);
			blockQueue.add(block);
		}
		System.out.println("Message has been received");
		System.out.println(count);
		this.conn.close();
		return blockQueue;
	}
	
	public void close() {
		try {
			this.publisher.close();
			this.subscriber.close();
			this.session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
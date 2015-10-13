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

public final class Messenger {
	private String topicName = "dynamicTopics/testT";
	private TopicConnection conn = null;
	private TopicSession session = null;
	private TopicPublisher publisher = null;
	private TopicSubscriber subscriber = null;
	private InitialContext context = null;
	private TopicConnectionFactory factory = null;
	private Topic topic = null;
	private MyMarshaller marshaller;
	
	public Messenger() throws NamingException, JMSException {
		this.context = new InitialContext();
		this.factory = (TopicConnectionFactory) this.context.lookup("TopicConnectionFactory");
		this.topic = (Topic) this.context.lookup(topicName);
		this.conn = this.factory.createTopicConnection();
		this.session = this.conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		this.publisher = this.session.createPublisher(this.topic);
		this.subscriber = this.session.createSubscriber(this.topic);
	}
	
	public synchronized void send(Block block) throws JAXBException, JMSException {
		// publish
		marshaller.marshalObject(block);
		ObjectMessage message = this.session.createObjectMessage(new File("block.xml"));
		this.publisher.publish(message);
	}
	
	public Queue<Block> receive() throws JMSException, JAXBException {
		// receive
		ObjectMessage objMessage = null;
		Message message = null;
		Queue<Block> blockQueue = new LinkedBlockingQueue<>();
		while ((message = this.subscriber.receive(10000)) != null) {
			//message = this.subscriber.receive(10000);
			objMessage = (ObjectMessage) message;
			blockQueue.add(this.marshaller.unmarshalObject((File)objMessage.getObject()));
		}
		return blockQueue;
	}
	
	public void close() {
		try {
			this.publisher.close();
			this.subscriber.close();
			this.session.close();
			this.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
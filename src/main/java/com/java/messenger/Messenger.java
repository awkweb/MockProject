package com.java.messenger;

import java.io.File;

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
	
	public void send(BlockBroker block) throws JAXBException, JMSException {
		// publish
		System.out.println("Inside of send function");
		this.marshaller.marshalObject(block);
		ObjectMessage message = this.session.createObjectMessage(this.marshaller.getBlockFile());
		this.conn.start();
		this.publisher.publish(message);
		System.out.println("Block message has been sent");
	}
	
	public BlockBroker receive(int numSecs) throws JMSException, JAXBException {
		System.out.println("Inside of receive function");
		ObjectMessage objMessage = null;
		Message message = null;
		File temp = null;
		BlockBroker block = null;
		//Queue<Block> blockQueue = new LinkedBlockingQueue<>();
		this.conn.start();

		/*while ((message = this.subscriber.receive(2000)) != null) {
			objMessage = (ObjectMessage) message;
			temp = (File)objMessage.getObject();
			block = this.marshaller.unmarshalObject(temp);
			blockQueue.add(block);
		}*/
		
		if((message = this.subscriber.receive(numSecs * 1000)) == null){
			System.out.println("No blocks received");
			return null;
		}
		objMessage = (ObjectMessage) message;
		temp = (File)objMessage.getObject();
		block = this.marshaller.unmarshalObject(temp);
		
		System.out.println("Message has been received");
		
		//return blockQueue;
		return block;
	}
	
	public void close() {
		try {
			this.publisher.close();
			this.subscriber.close();
			this.session.close();
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
	}
}
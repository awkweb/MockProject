package com.java.TestMarshallerFunctionality;

import java.util.Date;
import java.util.Queue;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import com.java.messenger.Messenger;
import com.java.pojo.Block;

public class TestMessagingFeature {

	public static void main(String[] args) throws NamingException, JMSException, JAXBException {
		Messenger messenger = new Messenger();
		Queue<Block> blockQueue = null;
		
		Block block = new Block("101", 100, (float)2.50, 200, "Open",
				(float)0.00, new Date(), 500);
		Block block2 = new Block("102", 200, (float)3.50, 300, "Closed",
				(float)1.00, new Date(), 600);
		
		messenger.send(block2);
		messenger.send(block);
		
		blockQueue = messenger.receive();
		System.out.println(blockQueue);

	}
	
}

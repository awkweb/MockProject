package com.java.blockXmlTest;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.java.pojo.Block;

public class UnmarshalXmlElementIntoBlockObject {
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Block.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Block block = (Block) jaxbUnmarshaller.unmarshal(new File("blocks.xml"));
		
		System.out.println(block.toString());
	}
	
}

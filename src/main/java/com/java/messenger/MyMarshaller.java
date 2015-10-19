package com.java.messenger;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.java.messenger.BlockBroker;

public class MyMarshaller {
	private File blockFile;
	private JAXBContext jaxbContext;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public MyMarshaller() throws JAXBException {
		this.blockFile = new File("block.xml");
		// subject to change --> May utilize Block or BlockQueue
		this.jaxbContext = JAXBContext.newInstance( BlockBroker.class);
		this.marshaller = jaxbContext.createMarshaller();
		this.unmarshaller = jaxbContext.createUnmarshaller();
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}
	
	/**
	 * Marshal...
	 * @throws JAXBException 
	 */
	public void marshalObject(BlockBroker blockBroker) throws JAXBException {
//		System.out.println("The block is " + block);
//		System.out.println("this.blockFile is " + this.blockFile);
//		System.out.println("blockFile is " + blockFile);
		this.marshaller.marshal(blockBroker, this.blockFile);
		System.out.println("Block has been marhsalled!");
	}
	
	/**
	 * Unmarshal...
	 * @throws JAXBException 
	 */
	public BlockBroker unmarshalObject(File fileBlock) throws JAXBException {
//		System.out.println("In unmarshal function");
		return (BlockBroker) this.unmarshaller.unmarshal(fileBlock);	
	}

	public File getBlockFile() {
		return blockFile;
	}

	public void setBlockFile(String fileName) {
		this.blockFile = new File(fileName);
	}
	
}

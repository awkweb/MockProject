package com.java.messenger;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.java.pojo.Block;

public class MyMarshaller {
	private File blockFile;
	private JAXBContext jaxbContext;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public MyMarshaller() throws JAXBException {
		this.blockFile = new File("block.xml");
		// subject to change --> May utilize Block or BlockQueue
		this.jaxbContext = JAXBContext.newInstance( Block.class);
		this.marshaller = jaxbContext.createMarshaller();
		this.unmarshaller = jaxbContext.createUnmarshaller();
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}
	
	/**
	 * Marshal...
	 * @throws JAXBException 
	 */
	public void marshalObject(Block block) throws JAXBException {
//		System.out.println("The block is " + block);
//		System.out.println("this.blockFile is " + this.blockFile);
//		System.out.println("blockFile is " + blockFile);
		this.marshaller.marshal(block, this.blockFile);
		System.out.println("Block has been marhsalled!");
	}
	
	/**
	 * Unmarshal...
	 * @throws JAXBException 
	 */
	public Block unmarshalObject(File fileBlock) throws JAXBException {
//		System.out.println("In unmarshal function");
		return (Block) this.unmarshaller.unmarshal(fileBlock);	
	}

	public File getBlockFile() {
		return blockFile;
	}

	public void setBlockFile(String fileName) {
		this.blockFile = new File(fileName);
	}
	
}

package com.java.TestMarshallerFunctionality;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBException;

import com.java.messenger.MyMarshaller;
import com.java.pojo.Block;

public class TestMarshallerFunctionality {

	public static void main(String[] args) throws JAXBException {
		File fileBlock = new File("block.xml");
		MyMarshaller mm = new MyMarshaller();
		Block block = new Block("101", 100, (float)2.50, 200, "Open",
				(float)0.00, new Date(), 500);
		
		mm.marshalObject(block);
		
		Block block2 = mm.unmarshalObject(fileBlock);
		System.out.println(block2.toString());
	}

}
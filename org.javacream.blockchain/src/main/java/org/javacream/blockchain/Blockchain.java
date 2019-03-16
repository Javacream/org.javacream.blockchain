package org.javacream.blockchain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class Blockchain implements Serializable{
	private static final String BEGIN = "##Begin##";
	private static final String END = "++End++";
	private static final long serialVersionUID = 1l;
	
	private LinkedList<Block> blockchain;
	
	public void init(){
		blockchain = new LinkedList<>();
		Random random = new Random(System.currentTimeMillis() + this.hashCode());
		byte[] firstHash = DigestUtils.md5(Double.toString(random.nextDouble()));
		Block firstBlock = new Block(firstHash, "".getBytes());
		blockchain.add(firstBlock);
	}
	
	public void addData(byte[] data) {
		validateData(data);
		Block newBlock = new Block(blockchain.getLast().getBlockHash(), data);
		blockchain.add(newBlock);
	}

	private void validateData(byte[] data) {
		if (data == null) {
			throw new IllegalArgumentException("invalid data");
		}
		String s = new String(data);
		if (!(s.startsWith(BEGIN) && s.endsWith(END))){
			throw new IllegalArgumentException("invalid data");
		}
	}

	public void validateChain() {
		for (Block block: blockchain) {
			block.selfCheck();
		}
	}
}
 
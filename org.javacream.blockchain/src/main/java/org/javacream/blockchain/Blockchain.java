package org.javacream.blockchain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

public class Blockchain implements Serializable{
	private static final String BEGIN = "##BEGIN##";
	private static final String END = "++END++";
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
	public byte[] getData(int index) {
		Block block = blockchain.get(index);
		block.selfCheck();
		return block.getData();
	}
	public List<byte[]> getAllData() {
		return blockchain.stream().map((block) -> {block.selfCheck(); return block.getData();}).collect(Collectors.toList());
	}
	public int size() {
		return blockchain.size();
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
 
package org.javacream.blockchain;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

public class Block implements Serializable{

	private static final long serialVersionUID = 1L;

	private byte[] parentBlockHash;
	private byte[] blockHash;
	private byte[] data;
	private long timestamp;
	public Block(byte[] parentBlockHash, byte[] data) {
		super();
		this.parentBlockHash = ArrayUtils.clone(parentBlockHash);
		this.data = ArrayUtils.clone(data);
		this.timestamp = System.currentTimeMillis();
		this.blockHash = toHash(parentBlockHash, data, Long.toString(timestamp).getBytes());
	}

	public byte[] getParentBlockHash() {
		return parentBlockHash;
	}

	public byte[] getBlockHash() {
		return blockHash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public byte[] getData() {
		return ArrayUtils.clone(data);
	}

	@Override
	public String toString() {
		return "Block [parentBlockHash=" + Arrays.toString(parentBlockHash) + ", blockHash="
				+ Arrays.toString(blockHash) + ", data=" + Arrays.toString(data) + ", timestamp=" + timestamp + "]";
	}

	public static byte[] toHash(byte[] data1, byte[] data2, byte[] data3) {
		byte[] dataAndHash = ArrayUtils.EMPTY_BYTE_ARRAY;
		dataAndHash = ArrayUtils.addAll(dataAndHash, data1);
		dataAndHash = ArrayUtils.addAll(dataAndHash, data2);
		dataAndHash = ArrayUtils.addAll(dataAndHash, data3);
		return DigestUtils.md5(dataAndHash);
		
	}

}

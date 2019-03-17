package org.javacream.blockchain;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TamperedBlockchainTest {
	private static final byte[] DATA1 = "##BEGIN##Space, ++END++".getBytes();
	private static final byte[] DATA2 = "##BEGIN##the final frontier++END++".getBytes();
	private static final byte[] DATA3 = "##BEGIN##These are the voyages of++END++".getBytes();
	private static final byte[] DATA4 = "##BEGIN##the starship Enterprise++END++".getBytes();
	private static final byte[] DATA5 = "##BEGIN##it's five year mission++END++".getBytes();
	private static final byte[] ATTACK_BYTES = "the starship discovery".getBytes();
	private Blockchain blockchain;

	@Before
	public void setUp() throws Exception {
		blockchain = new Blockchain();
		blockchain.init();
		blockchain.addData(DATA1);
		blockchain.addData(DATA2);
		blockchain.addData(DATA3);
		blockchain.addData(DATA4);
		blockchain.addData(DATA5);
	}

	@Test
	public void blockchainBlocksAreValid() {
		blockchain.validateBlocks();
	}

	@Test
	public void blockchainIsValid() {
		blockchain.validateBlockchain();
	}

	@Test
	public void attack() throws Exception {
		Field blockchainAttribute = Blockchain.class.getDeclaredField("blockchain");
		blockchainAttribute.setAccessible(true);
		List<Block> blocks = (List<Block>) blockchainAttribute.get(blockchain);
		Block block = blocks.get(4);
		Assert.assertArrayEquals(DATA4, block.getData());
		Field dataBlock = Block.class.getDeclaredField("data");
		Field parentBlockHash = Block.class.getDeclaredField("parentBlockHash");
		Field timestamp = Block.class.getDeclaredField("timestamp");
		Field blockHash = Block.class.getDeclaredField("blockHash");
		dataBlock.setAccessible(true);
		parentBlockHash.setAccessible(true);
		blockHash.setAccessible(true);
		timestamp.setAccessible(true);

		dataBlock.set(block, ATTACK_BYTES);
		Assert.assertArrayEquals(ATTACK_BYTES, block.getData());
		try {
			block.selfCheck();
			Assert.fail("block must be invalid");
		} catch (IllegalStateException e) {
			// OK
		}
		byte[] newHash = Block.toHash((byte[]) parentBlockHash.get(block), ATTACK_BYTES,
				Long.toString((long) timestamp.get(block)).getBytes());
		blockHash.set(block, newHash);
		block.selfCheck();
		blockchain.validateBlocks();
		try {
			blockchain.validateBlockchain();
			Assert.fail("blockchain must be invalid");
		} catch (IllegalStateException e) {
			// OK
		}
	}
}
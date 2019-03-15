package org.javacream.blockchain;

import org.junit.Assert;
import org.junit.Test;

public class BlockTests {
	private static final byte[] TEST_DATA = "Test".getBytes();
	private static final byte[] TEST_DATA2 = "Test2".getBytes();
	private static final byte[] TEST_PARENT_HASH = "Hash".getBytes();
	private static final byte[] TEST_PARENT_HASH2 = "Hash2".getBytes();
	private static final byte[] TEST_TIMESTAMP = "Time".getBytes();
	private static final byte[] TEST_TIMESTAMP2 = "Time2".getBytes();

	@Test
	public void blockIsCreatable() {
		new Block(TEST_PARENT_HASH, TEST_DATA);

	}

	@Test
	public void blockHasProvidedData() {
		Block block = new Block(TEST_PARENT_HASH, TEST_DATA);
		Assert.assertArrayEquals(TEST_DATA, block.getData());

	}

	@Test
	public void blockHasProvidedHash() {
		Block block = new Block(TEST_PARENT_HASH, TEST_DATA);
		Assert.assertArrayEquals(TEST_PARENT_HASH, block.getParentBlockHash());

	}

	@Test (expected=AssertionError.class)
	public void blocksHaveDifferentHashes() {
		Block block = new Block(TEST_PARENT_HASH, TEST_DATA);
		Block block2 = new Block(TEST_PARENT_HASH2, TEST_DATA2);
		Assert.assertArrayEquals(block2.getBlockHash(), block.getBlockHash());

	}
	@Test (expected=AssertionError.class)
	public void differentHashesForDifferentParentHashes() {
		byte[] hash1 = Block.toHash(TEST_PARENT_HASH, TEST_DATA, TEST_TIMESTAMP);
		byte[] hash2 = Block.toHash(TEST_PARENT_HASH2, TEST_DATA, TEST_TIMESTAMP);
		
		Assert.assertArrayEquals(hash1, hash2);
	}
	@Test (expected=AssertionError.class)
	public void differentHashesForDifferentData() {
		byte[] hash1 = Block.toHash(TEST_PARENT_HASH, TEST_DATA, TEST_TIMESTAMP);
		byte[] hash2 = Block.toHash(TEST_PARENT_HASH, TEST_DATA2, TEST_TIMESTAMP);
		Assert.assertArrayEquals(hash1, hash2);
	}
	@Test (expected=AssertionError.class)
	public void differentHashesForDifferentTimestamp() {
		byte[] hash1 = Block.toHash(TEST_PARENT_HASH, TEST_DATA, TEST_TIMESTAMP);
		byte[] hash2 = Block.toHash(TEST_PARENT_HASH, TEST_DATA, TEST_TIMESTAMP2);
		Assert.assertArrayEquals(hash1, hash2);
	}

}

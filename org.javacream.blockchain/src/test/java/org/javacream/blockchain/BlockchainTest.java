package org.javacream.blockchain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlockchainTest {
	private static final byte[] DATA1 = "##BEGIN##Space, ++END++".getBytes();
	private static final byte[] DATA2 = "##BEGIN##the final frontier++END++".getBytes();
	private static final byte[] DATA3 = "##BEGIN##These are the voyages of++END++".getBytes();
	private static final byte[] DATA4 = "##BEGIN##the starship Enterprise++END++".getBytes();
	private static final byte[] VALID_DATA = "##BEGIN##it's five year mission++END++".getBytes();
	private static final byte[] INVALID_DATA = "may the force be with you".getBytes();
	
	private Blockchain blockchain;

	@Before
	public void setUp() {
		blockchain = new Blockchain();
		blockchain.init();
		blockchain.addData(DATA1);
		blockchain.addData(DATA2);
		blockchain.addData(DATA3);
		blockchain.addData(DATA4);

	}

	@Test
	public void setupIsValid() {
		Assert.assertTrue(5 == blockchain.size());
	}
	@Test
	public void blockchainIsValid() {
		blockchain.validateChain();
	}
	@Test
	public void secondDataIsData1() {
		Assert.assertArrayEquals(DATA1, blockchain.getData(1));
	}
	@Test
	public void addingValidDataIsPossible() {
		blockchain.addData(VALID_DATA);
	}
	@Test(expected=IllegalArgumentException.class)
	public void addingInvalidDataIsImpossible() {
		blockchain.addData(INVALID_DATA);
	}
}

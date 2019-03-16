package org.javacream.blockchain;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {
	private static final int EXPECTED_HASH_SIZE = 32;
	private final String HASH_FOR_A = "7fc56270e7a70fa81a5935b72eacbe29";
	private final String HASH_FOR_THOUSAND_AS = "0f53217fc7c8e7f89e8a8558e64a7083";
	private final String HASH_FOR_THOUSAND_AS_ONE_REPLACED_WITH_B = "17fb81b6df838ee7693df7b49f6596d6";

	@Test
	public void createHashForCharacterA() {
		String hash = DigestUtils.md5Hex("A");
		Assert.assertEquals(EXPECTED_HASH_SIZE, hash.length());
		Assert.assertTrue(hash.equals(HASH_FOR_A));
	}

	@Test
	public void createHashForTenThousandAs() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			stringBuilder.append('A');
		}
		String hash = DigestUtils.md5Hex(stringBuilder.toString());
		Assert.assertEquals(EXPECTED_HASH_SIZE, hash.length());
		Assert.assertTrue(hash.equals(HASH_FOR_THOUSAND_AS));
	}

	@Test
	public void createHashForTenThousandAsReplaceOneB() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			stringBuilder.append('A');
		}
		stringBuilder.setCharAt(5678, 'B');
		String hash = DigestUtils.md5Hex(stringBuilder.toString());
		Assert.assertEquals(EXPECTED_HASH_SIZE, hash.length());
		Assert.assertTrue(hash.equals(HASH_FOR_THOUSAND_AS_ONE_REPLACED_WITH_B));
	}

}

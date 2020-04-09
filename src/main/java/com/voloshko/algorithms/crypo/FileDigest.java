package com.voloshko.algorithms.crypo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Cryptography I (Stanford University) Coursera course
 * Week 3 - Problem Set
 * <p>
 * Break file into 1K blocks.
 * It computes the hash of the last block and appends the value to the second to last block.
 *
 * @author avoloshko
 */
public class FileDigest {

  static byte[] getHash(String fileName) throws NoSuchAlgorithmException, IOException, DigestException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    try (RandomAccessFile file = new RandomAccessFile(new File(fileName), "r")) {
      final long length = file.length();

      final int blockSize = 1024;

      long blockEnd = length;
      long blockStart = blockEnd - (length % blockSize);
      if (blockStart == blockEnd) {
        blockStart = blockStart - blockSize;
      }

      byte[] bytes = new byte[blockSize + 32];

      while (blockEnd != 0) {
        file.seek(blockStart);
        int readBytes = (int) (blockEnd - blockStart);
        file.read(bytes, 0, readBytes);

        if (readBytes != blockSize) {
          md.update(bytes, 0, readBytes);
        } else {
          md.update(bytes);
        }
        md.digest(bytes, blockSize, 32);

        blockEnd = blockStart;
        blockStart -= blockSize;
      }

      return Arrays.copyOfRange(bytes, blockSize, bytes.length);
    }
  }
}

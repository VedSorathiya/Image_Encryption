# Image Encryption

This project uses AES (Advanced Encryption Standard), with CBC (Cipher Block Chaining) as the mode of operation.
The three parameters we need are:
- IV (initialization vector) - by using <a href="https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/IvParameterSpec.html">javax.crypto<a> library, we can specify an IV during each run.
- input data (file path)
- secret key (16 bits)

For indetailed working of AES, check out my blog : <a href="https://medium.com/techloop/cryptography-unveiled-exploring-the-shields-of-hashing-algorithms-and-encryption-techniques-195e1b842c62">Exploring Hashing & Encryption techniques</a>

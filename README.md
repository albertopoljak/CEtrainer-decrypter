# CEtrainer-decrypter

Decrypt , extract and open protected cheat engine trainers as simple .xml files.

## Installing

Download pre-built program from [Releases](https://github.com/albertopoljak/UnProtect/releases)

or 

Build the program yourself from the source code.

### Prerequisites

For running app you will need [Java Runtime Enviroment(JRE)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

## Usage

Open the file by clicking the "Browse" button, then click "Decrypt!".

If the process works you will get a decrypted trainer in the same folder as executable named "output.xml".
	
All file extensions are supported and the only thing that matters is that the file is protected  with CheatEngine algorithm.

If the file is not protected in that way it means that it is either not a CheatEngine trainer or it is using very old protection method(maybe even some newer method).

## Method explanation

Cheat engine trainers are protected with the following algorithm:

3 way pass XOR encryption and then Zlib compression with string "CHEAT" as file header.

This program simply reverses the steps in that algorithm to get to the source code.

This method doesn't execute the trainer in the procces. You can open it whenever you wish after the decryption.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

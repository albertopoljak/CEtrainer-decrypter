# Un-Protect

Open protected cheat engine trainers ( cetrainer , cheatengine , ct , trainer ...) and open them as simple xml. 

## Installing

Download pre-built program from [Releases](https://github.com/albertopoljak/UnProtect/releases)

or build the program yourself from the source code.

### Prerequisites

For running app you will need [Java Runtime Enviroment(JRE)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

## Usage

Open the file by clicking the "Browse" button, then click "Hack".

If the process works you should have readable file in .xml format at the same folder as executable named "output.xml".
	
All file extensions are supported and the only thing that matters is that the file is protected  with CheatEngine algorithm as follows:

3 way pass XOR encryption and then Zlib compression with string "CHEAT" as file header.

If the file is not protected in that way it means that it is either not a CheatEngine trainer or it is using old protection method(maybe even some newer method).

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

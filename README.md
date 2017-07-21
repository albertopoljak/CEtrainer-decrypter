# Un-Protect

Decrypt, dump and open protected .trainer or .cheatengine files as simple .xml file. 

## Getting Started

Fork/download/copy&paste code in any java IDE and it will work. Don't forget to include SWING library since it's used for GUI.
Link for Eclipse SWING editor: http://download.eclipse.org/windowbuilder/WB/integration/4.4/ 

## Installing

Download pre-built program from "Release" link.

### Prerequisites

For running app you will need JRE installed:
http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

## Usage

Open the file by inputing whole path, example C:\file.cetrainer  then click "Hack".
If the file is in the same folder as this executable then click "Get relative path" for easier input.
If the process works you should have readable file in .xml format at the same folder as executable named "output.xml".
	
All file extensions are supported and the only thing that matters is that the file is protected  with CheatEngine algorithm as follows:
3 way pass XOR encryption and then Zlib compression with string "CHEAT" as file header.
If the file is not protected in that way it means that it is either not a CheatEngine trainer or it is using old protection method(maybe even some newer method).
	
## Authors

Alberto Poljak

## Resources

Picture: Kung Fury Hackerman
	Song: Lost Years - West Side Lane

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

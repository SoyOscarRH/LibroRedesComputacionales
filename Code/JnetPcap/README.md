# Before Compile

Remember to install libpcap-dev with:
~~~~
sudo apt-get install libpcap-dev
~~~~

Remembet to put the .so a file in `usr/lib`



# Compile

~~~~
sudo javac -cp ":./jnetpcap.jar" File.java
~~~~



# Run

~~~~
sudo java -cp ":./jnetpcap.jar" File
~~~~
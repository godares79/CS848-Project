#! /bin/bash

echo "RUNNING INSTALLATION SCRIPT... REQUIRES USER PASSWORDS (UNLESS I USE KEYS)"

#Copy java and cassandra to the server from ugster02 to current directory
#Can copy over known hosts to avoid the pains of typing yes to ugster02
`scp d4dietri@ugster02.student.cs.uwaterloo.ca:/home/d4dietri/jdk-7u9-linux-x64.gz .`
`scp d4dietri@ugster02.student.cs.uwaterloo.ca:/home/d4dietri/cassandra.tar.gz .`

#Set up java first
echo "INSTALLING JAVA"
`sudo mkdir -p /usr/local/java`
`sudo tar xvzf jdk-7u9-linux-x64.gz`
`sudo mv jdk1.7.0_09 /usr/local/java`
`sudo cp /etc/profile /home/d4dietri/profile_backup`
`sudo sh -c "echo 'JAVA_HOME=/usr/local/java/jdk1.7.0_09' >> /etc/profile"`
`sudo sh -c 'echo "PATH=\\$PATH:\\$HOME/bin:\\$JAVA_HOME/bin" >> /etc/profile'`
`sudo sh -c "echo 'export JAVA_HOME' >> /etc/profile"`
`sudo sh -c "echo 'export PATH' >> /etc/profile"`
`sudo update-alternatives --install "/usr/bin/java" "java" "/usr/local/java/jdk1.7.0_09/bin/java" 1`
`sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/local/java/jdk1.7.0_09/bin/javac" 1`
`sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/local/java/jdk1.7.0_09/bin/javaws" 1`
`sudo update-alternatives --set java /usr/local/java/jdk1.7.0_09/bin/java`
`sudo update-alternatives --set javac /usr/local/java/jdk1.7.0_09/bin/javac`
`sudo update-alternatives --set javaws /usr/local/java/jdk1.7.0_09/bin/javaws`

echo "RELOADING GLOBAL PROFILE"
#`. /etc/profile`

echo "TESTING JAVA, SHOULD SEE JAVA INFORMATION DISPLAYED FROM THE NEXT TWO COMMANDS"
`java -version`
echo "DOES OUTPUT LOOK CORRECT? (java version 1.7.0_09)"
read tmp
`javac -version`
echo "DOES OUTPUT LOOK CORRECT? (java version 1.7.0_09)"
read tmp

#Setup cassandra
echo "SETTING UP CASSANDRA"
`tar xvzf cassandra.tar.gz`
`sudo mkdir /var/log/cassandra`
`sudo chown d4dietri /var/log/cassandra`
`sudo mkdir /var/lib/cassandra`
`sudo chown d4dietri /var/lib/cassandra`

ipaddr=`/sbin/ifconfig \
   | grep '\<inet\>' \
   | sed -n '1p' \
   | tr -s ' ' \
   | cut -d ' ' -f3 \
   | cut -d ':' -f2`
   
#Replace some the contents of conf/cassandra.yaml with this server specific
#settings
`sed -i "s/listen_address: 129.97.173.69/listen_address: $ipaddr/g" /home/d4dietri/apache-cassandra-1.1.6/conf/cassandra.yaml`
`sed -i "s/rpc_address: 129.97.173.69/rpc_address: $ipaddr/g" /home/d4dietri/apache-cassandra-1.1.6/conf/cassandra.yaml`

echo "INSTALLATION FINISHED"
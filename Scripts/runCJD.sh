#! /bin/bash
# Run the CJD on ugster02

echo "STARTING CJD"
`ssh ugster02 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 3
`ssh ugster02 'java -Djava.rmi.server.codebase=file:/opt/CJD/bin/ -Djava.rmi.server.hostname=ugster02 -Djava.security.policy=/opt/CJD/bin/server.policy -cp /opt/CJD/bin/:. CJD/CJDServer </dev/null >/dev/null 2>&1 &'`

#! /bin/bash
# This script will run the resource monitor on each machine
# in the cluster

echo "STARTING RESOURCE MONITOR ON UGSTER01"
`ssh ugster01 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster01 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER02"
`ssh ugster02 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster02 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER03"
`ssh ugster03 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster03 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER04"
`ssh ugster04 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster04 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER06"
`ssh ugster06 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster06 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER07"
`ssh ugster07 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster07 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER08"
`ssh ugster08 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster08 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER09"
`ssh ugster09 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster09 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER10"
`ssh ugster10 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster10 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
echo "STARTING RESOURCE MONITOR ON UGSTER11"
`ssh ugster11 '/usr/local/java/jdk1.7.0_09/bin/rmiregistry </dev/null >/dev/null 2>&1 &'`
sleep 2
`ssh ugster11 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main </dev/null >/dev/null 2>&1 &'`
#! /bin/bash
# This script will run the resource monitor on each machine
# in the cluster

echo "STARTING RESOURCE MONITOR ON UGSTER01"
`ssh ugster01 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER02"
`ssh ugster02 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER03"
`ssh ugster03 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER04"
`ssh ugster04 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER06"
`ssh ugster06 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER07"
`ssh ugster07 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER08"
`ssh ugster08 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER09"
`ssh ugster09 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER10"
`ssh ugster10 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
echo "STARTING RESOURCE MONITOR ON UGSTER11"
`ssh ugster11 'java -cp /opt/ResourceMonitor/bin/javasysmon-0.3.3.jar:/opt/ResourceMonitor/bin:. mainPackage/Main'`
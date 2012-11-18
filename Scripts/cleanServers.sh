#! /bin/bash
# Will clean out the log files and data on all of the cassandra nodes in
# the cluster
echo "CLEANING SERVER DATA"

`ssh ugster01 "rm -rf /var/lib/cassandra/*"`
`ssh ugster01 "rm -rf /var/log/cassandra/*"`

`ssh ugster02 "rm -rf /var/lib/cassandra/*"`
`ssh ugster02 "rm -rf /var/log/cassandra/*"`

`ssh ugster03 "rm -rf /var/lib/cassandra/*"`
`ssh ugster03 "rm -rf /var/log/cassandra/*"`

`ssh ugster04 "rm -rf /var/lib/cassandra/*"`
`ssh ugster04 "rm -rf /var/log/cassandra/*"`

`ssh ugster06 "rm -rf /var/lib/cassandra/*"`
`ssh ugster06 "rm -rf /var/log/cassandra/*"`

`ssh ugster07 "rm -rf /var/lib/cassandra/*"`
`ssh ugster07 "rm -rf /var/log/cassandra/*"`

`ssh ugster08 "rm -rf /var/lib/cassandra/*"`
`ssh ugster08 "rm -rf /var/log/cassandra/*"`

`ssh ugster09 "rm -rf /var/lib/cassandra/*"`
`ssh ugster09 "rm -rf /var/log/cassandra/*"`

`ssh ugster10 "rm -rf /var/lib/cassandra/*"`
`ssh ugster10 "rm -rf /var/log/cassandra/*"`

`ssh ugster11 "rm -rf /var/lib/cassandra/*"`
`ssh ugster11 "rm -rf /var/log/cassandra/*"`


echo "FINISHED CLEANING DATA"
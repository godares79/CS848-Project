#! /bin/bash
# Will shutdown cassandra on all nodes
# Assumes that the cassandra process pid handle is 'java' which isn't very nice,
# but necessary
# Also assumes that you have already stopped the cassandra process on ugster02
# (at least, it doesn't shut it down for you)

echo "STOPPING CASSANDRA NODES (EXCEPT ugster02)"
echo "SEE THE stop-cassandra SCRIPT IN THE CASSANDRA BIN FOR A BETTER APPROACH"

`ssh ugster01 'kill $(pidof java)'`
`ssh ugster03 'kill $(pidof java)'`
`ssh ugster04 'kill $(pidof java)'`
`ssh ugster06 'kill $(pidof java)'`
`ssh ugster07 'kill $(pidof java)'`
`ssh ugster08 'kill $(pidof java)'`
`ssh ugster09 'kill $(pidof java)'`
`ssh ugster10 'kill $(pidof java)'`
`ssh ugster11 'kill $(pidof java)'`


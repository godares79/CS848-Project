#! /bin/bash -x
# Will shutdown cassandra on all nodes
# Assumes that the cassandra process pid handle is 'java' which isn't very nice,
# but necessary
# Also assumes that you have already stopped the cassandra process on ugster02
# (at least, it doesn't shut it down for you)

echo "STOPPING CASSANDRA NODES (EXCEPT ugster02)"

`ssh ugster01 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster03 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster04 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster06 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster07 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster08 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster09 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster10 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
`ssh ugster11 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`

echo "STOPPING RESOURCE MONITOR NODES (and rmiregistry)"

`ssh ugster01 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster02 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster03 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster04 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster06 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster07 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster08 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster09 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster10 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster11 "pgrep -u \`whoami\` -f ResourceMonitor | xargs kill -9"`
`ssh ugster01 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster02 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster03 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster04 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster06 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster07 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster08 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster09 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster10 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`
`ssh ugster11 "pgrep -u \`whoami\` -f rmiregistry | xargs kill -9"`

echo "STOPPING CJD ON UGSTER02"
`ssh ugster02 "pgrep -u \`whoami\` -f CJD | xargs kill -9"`

echo "STOPPING CASSANDRA ON UGSTER02"
`ssh ugster02 "pgrep -u \`whoami\` -f cassandra | xargs kill -9"`
#! /bin/bash
# Start up cassandra on all of the nodes

echo "STARTING CASSANDRA..."

echo -ne "  UGSTER02..."
`/opt/apache-cassandra-1.1.6/bin/cassandra`
sleep 5
echo "DONE"

echo -ne "  UGSTER01..."
`ssh ugster01 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER03..."
`ssh ugster03 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER04..."
`ssh ugster04 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER06..."
`ssh ugster06 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER07..."
`ssh ugster07 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER08..."
`ssh ugster08 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER09..."
`ssh ugster09 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER10..."
`ssh ugster10 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo -ne "  UGSTER11..."
`ssh ugster11 '/opt/apache-cassandra-1.1.6/bin/cassandra'`
echo "DONE"

echo "CASSANDRA UP"
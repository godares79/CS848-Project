#! /bin/bash
# Will run the YCSB experiments for various thread numbers

for var in "$@"
do

  if [ "$var" == "ScheduledExperiments/" ]; then
    continue
  fi
  if [ "$var" == "BaselineExperiments/" ]; then
    continue
  fi

  index=1
  while [ $index -le 5 ]
  do
    `ssh d4dietri@ugster02 '/home/d4dietri/stopCassandra.sh'`
    `ssh d4dietri@ugster02 '/home/d4dietri/runCJD.sh'`
    `ssh d4dietri@ugster02 '/home/d4dietri/runResourceMonitor.sh'`
    `ssh d4dietri@ugster02 '/opt/apache-cassandra-1.1.6/bin/cassandra </dev/null >/dev/null 2>&1 &'`
    `ssh d4dietri@ugster02 '/home/d4dietri/startCassandra.sh'`
  
    sleep 60
	
    echo "$var"
    echo "***" >> $1/$var.output
    echo "Run_$index" >> $1/$var.output
    a=`bin/ycsb run cassandra-10 -P workloads/workloadb -P cassandra/$var -s 2>&1 > /dev/null`
    echo "$a" >> $1/$var.output
    (( index++ ))
  done

done
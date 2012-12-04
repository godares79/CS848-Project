#! /bin/bash
# Will run the YCSB experiments for various thread numbers

index=1
while [ $index -le 5 ]
do
  echo "$1"
	echo "***" >> $1.output
	echo "Run_$index" >> $1.output
	a=`bin/ycsb run cassandra-10 -P workloads/workloadb -P cassandra/$1 -s 2>&1 > /dev/null`
	echo "$a" >> $1.output
	(( index++ ))
done

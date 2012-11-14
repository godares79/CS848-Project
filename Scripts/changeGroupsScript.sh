#! /bin/bash
# For creating and changing the cassandra groups so that people can 
# run things
echo "CHANGING GROUP INFORMATION"

`sudo cp -r apache-cassandra-1.1.6 /opt/`
`sudo groupadd yabeda`
`sudo usermod -a -G yabeda d4dietri`
`sudo usermod -a -G yabeda y435liu`
`sudo usermod -a -G yabeda t32yu`
`sudo chgrp -R yabeda /opt/apache-cassandra-1.1.6`
`sudo chgrp -R yabeda /var/log/cassandra`
`sudo chgrp -R yabeda /var/lib/cassandra`
`sudo chmod -R 770 /opt/apache-cassandra-1.1.6`
`sudo chmod -R 770 /var/log/cassandra`
`sudo chmod -R 770 /var/lib/cassandra`

echo "COMPLETE"
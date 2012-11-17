#! /bin/bash
# Will loop through all servers and create a public keys so that
# no need to enter ssh password
echo "CREATING SSH KEY (USE AN EMPTY PASSPHRASE)"
`ssh-keygen -t dsa`

echo "ENTER SSH USERNAME:"
read usrName

`ssh ugster01 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster01:/home/$usrName/.ssh/authorized_keys`
`ssh ugster03 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster03:/home/$usrName/.ssh/authorized_keys`
`ssh ugster04 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster04:/home/$usrName/.ssh/authorized_keys`
`ssh ugster06 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster06:/home/$usrName/.ssh/authorized_keys`
`ssh ugster07 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster07:/home/$usrName/.ssh/authorized_keys`
`ssh ugster08 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster08:/home/$usrName/.ssh/authorized_keys`
`ssh ugster09 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster09:/home/$usrName/.ssh/authorized_keys`
`ssh ugster10 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster10:/home/$usrName/.ssh/authorized_keys`
`ssh ugster11 'mkdir /home/$usrName/.ssh'`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster11:/home/$usrName/.ssh/authorized_keys`




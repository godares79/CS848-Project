#! /bin/bash
# Will loop through all servers and create a public keys so that
# no need to enter ssh password
echo "CREATING SSH KEY (USE AN EMPTY PASSPHRASE)"
`ssh-keygen -t dsa`

echo "ENTER SSH USERNAME:"
read usrName

`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster01:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster03:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster04:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster06:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster07:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster08:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster09:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster10:/home/$usrName/.ssh/authorized_keys`
`rsync -ave ssh .ssh/id_dsa.pub $usrName@ugster11:/home/$usrName/.ssh/authorized_keys`




#! /bin/bash
# SCP a file to all of the nodes on the cluster (to your home directory)
echo "ENTER FILENAME:"
read fileName
echo "ENTER USERNAME:"
read usrName

`scp $fileName $usrName@ugster01:/home/$usrName/`
`scp $fileName $usrName@ugster03:/home/$usrName/`
`scp $fileName $usrName@ugster04:/home/$usrName/`
`scp $fileName $usrName@ugster06:/home/$usrName/`
`scp $fileName $usrName@ugster07:/home/$usrName/`
`scp $fileName $usrName@ugster08:/home/$usrName/`
`scp $fileName $usrName@ugster09:/home/$usrName/`
`scp $fileName $usrName@ugster10:/home/$usrName/`
`scp $fileName $usrName@ugster11:/home/$usrName/`
In eclipse, these need to be specified in the VM Arguments textbox, in the run configuration
for the RMI Server.

-Djava.rmi.server.codebase=file:/Users/daviddietrich/Programming/CS848/RMIExample/RMIServer/bin/
-Djava.rmi.server.hostname=<your hostname>
-Djava.security.policy=server.policy

Replace <your hostname> with the hostname of your computer.
Replace the C:/Users/david/... with the path to the bin folder of your RMI Server project.

Copy the server.policy to the bin/ directory as well.
Make sure that you run rmiregistry.exe (or rmiregistry in mac/linux) in a separate terminal.

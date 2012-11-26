#! /usr/bin/python
# This script will take the data located in the file passed in, and create a 
# graph using gnuplot for the data
# Assumes time vs operations per/sec

import sys, string, os, re, copy

fname = sys.argv[1]
lines = [line.strip() for line in open(fname)]
newSection = re.compile('\*\*\*')
sectionName = re.compile('^\S+$')
regExp = re.compile('[\d]+')

#totalList = list()
itemsList = list()
namesList = list()

for line in lines:
	if newSection.match(line):
		#totalList.append(copy.deepcopy(itemsList))
		#itemsList = list()
		print "In a new section"
	elif sectionName.match(line):
		print "New section name = " + sectionName.search(line).group()
		namesList.append(sectionName.search(line).group())
	elif regExp.match(line):
		matches = regExp.findall(line)
		itemsList.append([int(matches[0]), int(matches[2])])

#print itemsList

# Create a data file holding the data
itemsList.sort(key=lambda k: (k[0]))
#print itemsList

datFile = open("data.dat", 'w')
previous = 0
for item in itemsList:
	if item[0] == previous:
		datFile.write(" ")
		datFile.write(str(item[0]))
		datFile.write(" ")
		datFile.write(str(item[1]))
	else:
		previous = item[0]
		datFile.write("\n")
		datFile.write(str(item[0]))
		datFile.write(" ")
		datFile.write(str(item[1]))
datFile.close()

# Create the script file
plotFile = open("data.plot", 'w')
plotFile.write("#! /usr/bin/gnuplot\nset terminal png linewidth 1\nset output \"out.png\"\nset title \"Experiment Performance\"\nset xlabel \"Time\"\nset ylabel \"Operations/second\"\n")

plotString = "plot "
index = 1
for name in namesList:
	plotString += "\"data.dat\" using " + str(index) + ":" + str(index+1) + " with linespoints title \"" + name + "\", "
	index=index+2
	
plotString = plotString[:-2]
plotFile.write(plotString + "\n")
plotFile.close()
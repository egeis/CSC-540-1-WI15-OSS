# CSC-540-1-WI15-OSS
Operating System Simulation using different scheduling algorithms

The program implements the following CPU scheduling algorithms. 

1.	First-Come-First-Serve (FCFS)
2.	Shortest-Job-First (SJF)
3.	Round-Robin with time slice = 3 (RR-3)
4.	Round-Robin with time slice = 5 (RR-5)
	
The program will read process burst times from a file (job.txt)

A sample input file of four jobs is given as follows (burst time in ms).:
[Begin of job.txt]
Job1
6
Job2
4
Job3
10
Job4
7
[End of job.txt]

Note: You can assume that
(1)	 there are no more than 30 jobs in the input file (job.txt).
(2)	 processes arrive in the order they are read from the file for FCFS, RR-3 and RR-5.
(3)	 all jobs arrive at time 0 for SJF.

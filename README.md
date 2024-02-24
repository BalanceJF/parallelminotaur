*********************************
# Parallel Minotaur Readme
*********************************

This program was written for a university assignment. Because I enjoy your curiosity so much, I've decided to explain how to run my amazing programs!

1. From the command prompt, cd into the directory where you clone this repository.
2. Type the following commands:

For assignment 1:
```
javac ParallelMinotaur.java Labyrinth.java Victim.java
java ParallelMinotaur
```

For assignment 2:
```
javac ParallelMinotaurTwo.java Showroom.java VictimTwo.java
java ParallelMinotaurTwo
```

3. Wait.
4. Your desired output should appear in the console! Feel free to change the numVictims variable in the ParallelMinotaur.java file as desired, or the numVictims and numIterations variables in the ParallelMinotaurTwo.java file as desired.

*******************************
## Summary of approach:
*******************************

### Assignment 1:
For my thread system, I decided to use a reentrant lock from Java libraries since this allowed me to lock the labyrinth doors until the next person was ready to go in. The threads still had the inability to see each other, but they had to go in sequence since this is part of the problem. I can be 100% sure that every victim has entered the labyrinth at least once, because the only person changing the state of the cupcake to being eaten is the Hungry Hungry Hippo, and everyone else only replenishes the cupcake once, meaning that the only way the Hungry Hungry Hippo could accomplish the feat of eating the cupcake n number of times (n being the number of victims) is if every other person already visited and did their one replenish. This does mean the runtime is relatively high, being technically an average of O(n^2) as Mr. HHH visiting n times with an equal likelihood distribution will happen around the same time everyone has visited n times (thus n * n). I tested multiple different guest numbers and this confirms my estimates and logic.

### Assignment 2:
Strategies:
1. Allowing guests to check the viewing room any time would require very little overhead and be much simpler to maintain. However, it would also be unfair, since someone who checked multiple times and for much longer would be just as likely to get in next as someone who randomly decided on a whim to go check. As mentioned, it would also require the guests to go out of their way to specifically check instead of allowing them to easily and quickly know they still can't go, meaning there may be some downtime between processes.

2. The strategy of a mark on the room showing if it's available or not solves the problem described for the first strategy involving time spent checking, as guests would know without any guesswork that it's already occupied. It still has the problem of fairness from the first strategy.

3. A strategy of using a queue solves the fairness problem the two other strategies have. Furthermore, it ensures that no single guest is responsible for checking the status of the room themselves, instead being told. The main disadvantage of a queue is that it is the highest amount of overhead, with a rigid locking system that could potentially hurt performance for extremely large test cases.

I believe strategy 3 is the best and what I employed in my program. The numbers we are dealing with aren't massive, so overhead isn't as much of an issue as fairness is. I have a queue system that blocks people and only lets the next person know once the previous person is done. Meanwhile, I'm also letting new people enter the line and wait until it's ready for them. This continues until both of these processes are done, and each thread is allowed to take its sweet time whenever it begins its viewing period. I tested the program using various inputs for the number of guests and iterations and it works as expected.

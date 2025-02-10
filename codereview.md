# Code Review on "Two card games"

## Documentation
As mentioned in the assignment, this code does not have sufficient documentation, so it's hard to 
tell what's going on without the README. So I added Javadoc comments
for most methods (besides getters/setters/etc) and the attributes. 

## Code structure
### Does the code satisfy all provided specifications? Is the code consistently formatted?
After following the README file with the code, I think. The poker game does not have a way to declare a winner, but
that wasn't an explicit specification I think. The code formatting was fine, but I used the format document command to make sure. A small thing is in the `turn()` class in `LamarckianPoker`, the first player's sacrificial card is placed in the pool instead of the discard pile (which means that the second player could pick it up). See line 171 in `LamarckianPoker`. 
```
discard.getDeck().add(firstCard);
```

### Do all methods have a clear purpose? Is there any debugging code that should be commented out?
Yes, I think the methods have a clear purpose. There was an `iTurn` variable in the `LamarckianPoker` class that seemed to track the number of turns during the game, but it didn't seem to be used anywhere in the results, so I commented it out.

### What repeated code is present that could become a single method? 
Most of the code seems not-repetitive and short. The `turn()` method in the `LamarckianPoker` class looks suspicious, though. When it is deciding which player has the higher sacrifical card, the code seems repetitive. We already have a method for that—the `compareTo` method in the `Card` class. See lines 118-128 in `LamarckianPoker`.
```
    if (player1Card.compareTo(player2Card) > 0) {
        firstHand = player1Hand;
        secondHand = player2Hand;
        firstCard = player1Card;
        secondCard = player2Card;
        } 
        else {
        firstHand = player2Hand;
        secondHand = player1Hand;
        firstCard = player2Card;
        secondCard = player1Card;
        }
```
This should work, since before, the code was basically rewriting the `compareTo()` function.

### Are there any “magic number” constants that should be redefined as final variables? 
Both in `Blackjack` and `LamarckianPoker`, there are some important numbers that will never change. For example, the number of cards that are in the pool in Lamarckian Poker (4) or the bust number in Blackjack (21). Instead of using these numbers in the if conditions and loops, they could be declared as final and static variables, since they do not change from one object to another.

### Is there any complexity that can be simplified by the use of multiple, clearer methods?
In the `turn()` method in the `LamarckianPoker` class, there is some repetition. The turns of the first player and second player are pretty much the same. To simplify this code, it might be good to have a separate method that does the repeated work and just call that method twice with the first and second hand. I didn't end up changing the code, but note how lines 158-173 is very similar to lines 175-186.

## Variables

### Do all variables have reasonable types and identifiers? Does each variable have a single purpose in its scope?
I think so.

### Whenever the code assigns a value to a variable, does the code ensure type consistency? Are there variables that can be removed from the code because they are redundant or unused?
I think the types are consistent. I already mentioned this, but I removed the `iTurns` variable from the `LamarckianPoker`
class since it seemed to have no use in the end.

## Loops and conditional statements
I couldn't find errors in this category. There are not super long `if-else` chains that are not already in `switch` form. Initializations look correct. Lamarckian Poker games can go on for a long time, but they should end at some point, so the `while` loop in the driver program should terminate at some point. 

## General programming practice
I think it looks good overall. `for-each` loops were used most of the time, and when for loops were used, the bounds were correct. Return values are correct. No methods are printing anything.

## `IllegalArgumentException`

### Why the error occurs
The exception comes with a message "bound must be positive." I think the error comes in the `rand.nextInt(player1Hand.size())` or 
`rand.nextInt(player2Hand.size())` call in the `turn()` function in the LamarckianPoker class.
What probably happened is sometimes, a player will end up with no cards, so the size of their hand is 0. 
Then `rand.nextInt(0)` is called, which causes Java to throw an exception.<br>
<br>
This exception does not happen all the time. It is likely that neither player will run out of cards and that both will 
reach 7 cards. In this case, the game ends smoothly, and no exception is thrown.

### How to fix it 
The fix can be simple. If a player has no card, they lose, and the game is 0. So `turns()` can return false, and the game will be terminated
before the random function is called. Although it may seem obvious, 
it may be good to add that someone loses if they run out of cards in the project specifications.

## `IndexOutOfBoundsException`
### Why the error occurs
I think this happens when there is a call to the `deal()` method in the Deck class, but there is no more cards in the deck.
This leads to trying to remove an element in an `ArrayList` when there are no elements in the list, causing the exception ("Index 0 out of bounds for length 0"). 
Similar to the last exception, this doesn't happen all the time. The game often ends before they players reach the bottom of  the deck. Also, in line 34-36 of the driver program, it resets the deck if it has less than 10 cards.
```
if (game.getDeck().size() < 10) {
    game.reset(true);
}
```
### How to fix it 
You could increase the number 10 to a number such that it is guaranteed that the player/dealer will end their turn
before the deck runs out of cards (could probably be calculated considering worst case scenario when the cards have really low value) for an easy fix. Otherwise, in the `deal()` method, you could make it so that if the deck is out of cards (the deck's size is 0), the `reset()` method is called so there is a new stack of cards.
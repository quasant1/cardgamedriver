# Code Review on "Two card games"

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
This happens when 

### How to fix it 
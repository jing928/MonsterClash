# MonsterClash
Monster Clash is a two-player turn-based board game inspired by the Pixar animation movie *Monsters University* [1]. 

## Game Play

Main characters are monsters from two different teams - Oozma Kappa, the nerdy bookworms and Roar Omega Roar, the evil cool guys.

Players pick the team first and then select 3 monsters from the team roster. Each monster has its own strengths and weaknesses and special abilities, therefore different combinations of monsters would require different strategies.

When game starts, monsters from two players will be initialized at the opposite side of the board. Players take turns to roll the dice and move the monsters across the board towards the opponent’s home line. Along the way, the monsters can attack and kill each other.

All the cells look similar but some might be hidden traps. When a monster steps on it, some unfortunate events will happen, e.g. stay frozen for X turns, move back to start line, decrease health by X, etc…

Players have to figure out the best strategy to win the game, they can either play defense and try to sneak a monster to the opponent's home or they can play offense and start a killing spree.

However you choose to play, the game board is yours, and let the monsters clash!



## Rules

### Winning Conditions:

The team wins when __any__ of the following scenarios happen.

1. Any monster of the team moves to the opponent's home line. The dice value has to be exact for the monster to move to the home line, therefore luck is also a factor, like any game:)
2. The team kills all 3 monsters from the other team. The game automatically ends when the last standing monster is killed.

### General Rules:

- Two players cannot pick the same team.
- Monsters can move towards any directions but cannot move out of the border lines of the game board.
- Player rolls the dice first and then pick a monster to move and act.
- Each turn a player can only pick one monster.
- The monster has to move first and act second.
- After the move, the player can choose to attack __or__ use special skill of the selected monster, provided the monster has any special skill.
- If no other monsters are within attack range, the player can choose to use special skill or forfeit the action for this turn. (If the selected has no special skill, the player has to forfeit the action.)
- A monster cannot invoke harmful actions (ones that decrease the health of the recipient) towards monsters of the same team. A monster can invoke other actions towards anyone.
- If a cell is occupied by other monsters, no other monsters can move to that cell.
- Cell actions have higher precedence. For example, if a monster moves to a trap cell, which freezes the monster for 2 turns, then for that turn, the monster can no longer attack or use special skill.
- Each turn a monster has to move, unless all monsters are frozen by trap cells or other similar events.
- The attack damage is calculated as `(AttackPower of the Attacking Monster) - (ShieldStrength of the Attacked Monster)`
- Distance is calculated using Manhattan Distance algorithm.



[1] *Disney presents a Pixar Animation Studios film ; directed by Dan Scanlon ; produced by Kori Rae ; story by Dan Scanlon, Daniel Gerson & Robert L. Baird ; screenplay by Daniel Gerson & Robert L. Baird, Dan Scanlon. Monsters University. Burbank, CA :Distributed by Buena Vista Home Entertainment, 2013. Print.*


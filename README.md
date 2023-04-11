
Members: Haruka Mibuchi, Gurinder Bhogal, Asifiwe Julio Patrick

## Escape from Paparazzi

Paparazzi are trying to take pictures of a celebrity visiting a shopping mall. The celebrity also wants to avoid all the cameras that have been set in the mall by those paparazzi. In this 2D game, the celebrity must collect all disguise items from stores in the mall to escape from paparazzi and their cameras. Therefore, the player will move the celebrity in complex and branched passages and lead her to the exit obtaining all the items without meeting paparazzi and cameras.

### Instruction of the game

- Use the arrow or WASD keys to move the celebrity/the main character left, right, dP or down
- The player will gain 10 points for each disguise item and 20 points when the player meets a fan
- The player will lose 20 points if the celebrity crosses the camera lazer
- The player will be able to exit from the mall after getting all the items
- The player will lose the game if the celebrity loses all the score
- Use 'Esc' to pause the game
- Click 'START' to start the game
- Click 'RESUME' to restart the game
- Click 'RESTART' to play the game again

### How to build the game

In order to build this game, you will be required to install Java, Maven, and Git.

### How to install the game

- In order to run this application, Download the GitLab zip file or clone the repository.

- You can clone by typing `git clone https://csil-git1.cs.surrey.sfu.ca/cmpt276f22_group18/project.git`

### How to run the game

- Build the project by running the maven command `mvn package` or `mvn clean install`

- After you successfully build the project, type the command `java -jar target/maze_game-0.0.1-SNAPSHOT.jar` to run the game

### How to test

- You can test the game by using the command `mvn test` after building the project

### How to generate Javadocs

- run the command `mvn javadoc:javadoc`

- Open the `index.html` file in `target/site/apidocs`, in your browser

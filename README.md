# Dice Games

A simple game app for a mobile app development course. Students will be able to demonstrate their understanding of:

* Activity lifecycle
* Intents
* Persisting the UI state
* Developing UI for landscape and portrait mode

### a. Basic Information

**Name of the Project** - Dice Games App

**Name of Student** - Dev Goel

**BITS ID** - 2019A7PS0236G

**Email** - f20190236@goa.bits-pilani.ac.in

### b. What does the app do? Any known bugs?

The Dice Games App simulates a die rolling game which rewards the player with some fixed coins when they get a 6 on the die. Players can then wager these coins in the Two Or More game and have a chance to win more coins based on the game type they choose.
The following bug(s) exist in the app:

* The wager field currently checks for low values of wager and within range of Integer. If we were to achieve a balance high enough to set the wager at a value above the maximum possible Integer value, an Integer Overflow would occur which trigger the Toast error message (which otherwise should have been valid).
* However, since we are not achieving such high values, we can safely say that there are no bugs under the given constraints.

### c. Description of completed tasks and steps followed to achieve them

**Task 1 - Developing WalletActivity**

* I store the views of each display field or button under named variables in the **WalletActivity.java** file.
* Declared a View Model which will help persist the UI state across device rotations.
* Used **onSaveInstanceState** to store coin balance and restore in case of process death.
* Called the appropriate functions for setting the die and winning value.
* Called the **rollDie** method on clicking the Die.

**Task 2 - Developing TwoOrMoreViewModel**

* I implemented the given setter and getter functions.
* Implemented **isValidWager()** function which checks if wager is valid by comparing with appropriate values for 2 alike, 3 alike and 4 alike game types.
* Stored and returned dice values in an **ArrayList**.
* The function **checkSame()** checks for the maximum number of similar die, which then returns the value for further play.
* Implemented the **play()** function, which simulates playing of the game by rolling the die, checking valid wager, and returning the result.

**Task 3 - Connecting with TwoOrMoreActivity**

* Stored the views of each display field or button under named variables in the **TwoOrMoreActivity.java** file.
* Implemented the **TwoOrMoreViewModel** to connect the activity with the model. This model will help us to persist UI state across screen orientation changes.
* Maintained UI state across process death by using **Bundle** and **onSaveInstanceState**.

**Task 4 - Implementing the 'Back' button**

* The 'Back' button passes an intent back to the **WalletActivity** with the new balance attached to it.
* The **WalletActivity** receives the intent and updates its balance by calling the setter function for balance.

**NOTE** - I have **ALSO** implemented persistence of state via the device's physical back button. Users can now navigate throughout the app via the device's back button without any loss of data.

**Task 5 - Implementing the 'Info' button**

* The 'Info' button launches a new activity by passing an intent.
* The **InfoActivity** then returns an intent via the back button implemented. The user can also go back via the physical back button as there is no UI changes involved in this activity.

**Task 6 - Developing Landscape Layouts**

* I created landscape layouts for **WalletActivity** and **TwoOrMoreActivity**, with proper styling.
* The UI state persists over rotation as well as process death due to implementations of ViewModel and Bundle respectively.

**Task 7 - Accessibility**

* I ran the application using **TalkBack** service. The service is fairly easy to use and guides the user on each and every step. The wager input element was recognized by TalkBack as input box, and the hint text present by default in the element was read aloud. Tapping once on the displayed result read aloud the text to the user. I could easily understand where my finger was on the screen and which element was active. Overall, TalkBack is a great accessibility tool which can help blind people or people with vision impairments to use apps.

* I ran **Accessibility Scanner** on the application, which reported the following issue:
 * Item Descriptions - Multiple items have the same description.
 * This was fixed by adding 4 different values in **strings.xml** file, and using those as **contentDescription** for the particular elements.

### d. Testing using written test cases and monkey stress-testing

* I followed a test-driven approach for this assignment, writing and running test cases as I worked on the edge cases for the code.
* I wrote instrumented / UI tests and ran all tests after each step I completed in the assignment, while adding new test cases.
* I ran DiceTest, TwoOrMoreViewModelMockitoTest, TwoOrMoreViewModelTest, WalletViewModelTest and InstrumentedTest successfully on the app. All tests passed on my app.
* For monkey stress testing, I ran the monkey tool successfully for 20000 iterations using the command `adb shell monkey -p androidsamples.java.dicegames -v 20000`.

Running tests on my app after each step helped me get a more detailed idea as to how the app was functioning, and also helped me figure out some corner cases which I corrected to avoid app crashes.
I ran the monkey tool successfully for 2500, 5000, 10000, and 20000 iterations. The app did not crash on any run.

### e. Approximate number of hours it took to complete the assignment

**Writing Code, Testing and Solving Accessibility Issues** -> 8 hours

**Documentation** -> 1 hour

**Total Time = 9 hours**

### f. Difficulty of Assignment

On a scale of 1 to 10, I would rate the difficulty of the assignment as **7**.

I feel the assignment was moderately difficult, and the integral part was the maintenance of UI state across device rotations. The assignment gave me a good understanding of the important functionalities and methods commonly used in App Development.
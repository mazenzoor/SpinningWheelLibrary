# SpinningWheelLibrary


### Implementation

#### Create a Wheel object using constructors

```java

  Wheel myWheel = new Wheel(); 
 ```
 
 #### Constructor Parameters _[ optional ]_
 
 ```java

  Wheel myWheel = new Wheel(ImageView wheelImageView, 
                              int nbOfSlices 
                              [int nbOfSpins, int animationDuration]); 
   ```
  
  - **wheelImageView** : The ImageView from layout (only the circular part)
    
  - **nbOfSlices** : The number of slices in your wheel (start count from 1)
  
  - **nbOfSpins** : How many rotations to animate before landing on the destinatino slice
  
  - **animationDuration** : In milli seconds the duration of the entire spin animation
  
  
  
  #### Spinning The Wheel
  
  Call the spinTo method and pass in the index (starting from 0)
  
   ```java
    
    spinTo(int index)
   ```

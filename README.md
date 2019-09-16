# SpinningWheelLibrary


## Import in your project
1. Add in root build.gradle file
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
	}
}
 ```

2.Add the dependency
```gradle
dependencies {
   implementation 'com.github.mzpro10:SpinningWheelLibrary:1.0'
}
```
 <br> <br>
## Implementation

### Create a Wheel object using constructors

```java

  Wheel myWheel = new Wheel(); 
 ```
 
 ### Constructor Parameters _[ optional ]_
 
 ```java

  Wheel myWheel = new Wheel(ImageView wheelImageView, 
                              int nbOfSlices 
                              [int nbOfSpins, int animationDuration]); 
   ```
  
  - **wheelImageView** : The ImageView from layout (only the circular part)
    
  - **nbOfSlices** : The number of slices in your wheel (start count from 1)
  
  - **nbOfSpins** : How many rotations to animate before landing on the destinatino slice
  
  - **animationDuration** : In milli seconds the duration of the entire spin animation
  
  <br><br>
  
  ### Spinning The Wheel

  Call the spinTo method and pass in the index (starting from 0)
  
   ```java
    myWheel.spinTo(int index)
   ```


### Implement Callback

When the animation ends a callback method can be called

1. In your class implement **WheelCallback** interface
2. Create a WheelCallback instance where you can write your code to execute after animation ends
3. Set the callback instance you created by calling:
   ```java
    myWheel.setCallback(myCallback);
   ```
  
  
  
  ### Additional Methods
  
  - setWheel (ImageView wheel)
  - setNumberOfSlices (int number_of_slices)
  - setGoToIndex (int go_to_index)
  - setSpinFactor (int spin_factor) -- set the number of rotations for the animation
  - setInterpolator (double a, double b, double c, double d) -- Create a pathinterpolator for the animation
  
  
  
  ### End
  

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;

public class Wheel {

    ////  The Wheel class wraps all the code required
    ////  to create and animate the wheel functionality


    //// The image view of the wheel
    protected
    ImageView wheel;


    //// Number of slices on the wheel
    protected
    int number_of_slices = 0;


    //// The current slice index where the wheel rests
    protected
    int current_index = 0;


    //// The index the wheel should spin to
    protected
    int go_to_index = 0;


    //// The amount of offset the wheel is on in degrees
    protected
    float offset = 0;


    //// The amount of degrees must spin to get to destination
    protected
    float rotate_by_degrees = 0;


    //// The degree value of the go to index
    protected
    float go_to_degrees = 0;


    //// BLock spin button during animation
    protected
    boolean isAnimating = false;


    //// The amount of 360 rotations the wheel
    //// will rotate
    protected
    int spin_factor = 3;


    //// The time it takes for the wheel
    //// to complete animating
    protected
    int animation_duration = 3000;


    //// Interpolator to ease the animation
    protected
    Interpolator interpolator =
            PathInterpolatorCompat.create(0.28f,-0.12f,0f,1.05f);


    //// Wheel Callback object that will be called
    //// when the animation ends, also sends to user
    //// where the wheel has ended animating to (slice index)

    WheelCallback callback = null;



    //// Public constructor that takes no input
    //// In case the user needs to create the wheel
    //// now, but set the variables later

    public
    Wheel() {}


    //// The constructor that initializes everything
    //// Required params are
    //// wheel : Image View of the wheel
    //// spinButton : Button that will trigger the spinning
    //// nbOfSlices : integer of the number of slices in the wheel

    public
    Wheel(ImageView wheelImageView, int nbOfSlices) {

        this.wheel = wheelImageView;

        this.number_of_slices = nbOfSlices;

    }


    //// Same constructor that takes an extra
    //// parameter to specify the number of
    //// spins the wheel will do before stopping

    public
    Wheel(ImageView wheelImageView int nbOfSlices, int nbOfSpins) {

        this(wheelImageView, nbOfSlices);

        this.spin_factor = nbOfSpins;

    }


    //// Same constructor that takes an extra
    //// parameter to specify the animation duration

    public
    Wheel(ImageView wheelImageView, int nbOfSlices, int nbOfSpins, int animationDuration) {

        this(wheelImageView, nbOfSlices, nbOfSpins);

        this.animation_duration = animationDuration;

    }


    //// The spinTo method accepts an index
    //// and will (animate) spin the wheel to
    //// that index

    public void spinTo(int index) {


        go_to_index = index;

        //// If the wheel is not initialized or it
        //// is currently animating don't spin

        if ( wheel == null || isAnimating)
            return;


        //// Set is animating to true

        isAnimating = true;


        //// Calculate the destination degrees

        go_to_degrees = ( 360f / number_of_slices ) * go_to_index;


        //// Calculate the amount of degrees the wheel must rotate
        //// to get to destined slice

        rotate_by_degrees = (360f / number_of_slices) * (go_to_index - current_index);


        //// Make sure the wheel always spins in a clockwise direction

        if(current_index > go_to_index)
            rotate_by_degrees =  360 + (current_index * -1);


        //// Create the object animator and set
        //// all params

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                wheel,
                "rotation",
                offset,
                go_to_degrees + ( 360 * spin_factor)
        );

        wheel.setPivotX(wheel.getMeasuredWidth() / 2f);

        wheel.setPivotY(wheel.getMeasuredHeight() / 2f);

        animator.setInterpolator(interpolator);

        animator.setDuration(animation_duration);

        animator.start();

        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                //// Animation ended, update old index to new index and offset

                current_index = go_to_index;


                //// Update the offset to be on the new slice location

                offset = ( 360f / number_of_slices ) * current_index;


                //// Done Animating bool

                isAnimating = false;


                //// IF the user implemeted a callback, call
                //// here and send the slice it is currently on

                if (callback != null)
                    callback.doneAnimating(current_index);

            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });


    }




    //// ALL Getters and Setters


    //// Set the callback object
    public void setWheelCallback(WheelCallback wheelCallback) {

        this.callback = wheelCallback;

    }

    public void setWheel(ImageView wheel) {
        this.wheel = wheel;
    }


    public void setNumberOfSlices(int number_of_slices) {
        this.number_of_slices = number_of_slices;
    }

    public void setGoToIndex(int go_to_index) {
        this.go_to_index = go_to_index;
    }


    public void setSpinFactor(int spin_factor) {
        this.spin_factor = spin_factor;
    }

    public void setInterpolator(double a, double b, double c, double d) {
        this.interpolator = PathInterpolatorCompat.create(
                (float) a,
                (float) b,
                (float) c,
                (float) d
                );
    }
}

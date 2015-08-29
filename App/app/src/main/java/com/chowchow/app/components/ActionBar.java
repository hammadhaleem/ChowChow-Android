package com.chowchow.app.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chowchow.app.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jackychan on 29/8/15.
 */
public class ActionBar extends RelativeLayout {

    private static final String TAG = "ActionBar";

    private enum Animation {
        SLIDE_UP,
        SLIDE_DOWN
    }

    private Context context;
    private View view;

    @Bind(R.id.action_bar_items_selected)
    TextView itemsSelected;

    @Bind(R.id.action_bar_send)
    ImageButton sendButton;

    boolean isTriggered;

    public ActionBar(Context context) {
        super(context);
        init(context);
    }

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        isTriggered = false;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.action_bar, this, true);
        ButterKnife.bind(this, view);

    }

    public void trigger(boolean trigger) {
        isTriggered = trigger;
        if (isTriggered) {
            animate(Animation.SLIDE_UP);
        } else {
            animate(Animation.SLIDE_DOWN);
        };

    }

    private void animate(Animation animation) {
        if (animation.equals(Animation.SLIDE_UP)) {
            YoYo.with(Techniques.SlideInUp)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .duration(300)
                    .playOn(this);
        } else if (animation.equals(Animation.SLIDE_DOWN)) {
            YoYo.with(Techniques.SlideOutDown)
                    .duration(300)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(this);
        }

    }

    @OnClick(R.id.action_bar_send)
    void onSendButtonPressed() {
        Log.d(TAG, "onSendButtonPressed()");

        // clean up
        trigger(false);
    }


    public void setItemsSelected(String text) {
        Log.d(TAG, "setItemsSelected");
        itemsSelected.setText(text);
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public static String getTAG() {
        return TAG;
    }
}

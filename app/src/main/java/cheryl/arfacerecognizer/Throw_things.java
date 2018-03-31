package cheryl.arfacerecognizer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by L850 on 2018/3/31.
 */

public class Throw_things extends RelativeLayout {
    View view;
    int scX , scY;
    int iniX , iniY;
    int w, h;
    public Throw_things(Context context) {
        super(context);
        view = new View(context);
        addView(view);


        view.getLayoutParams().width = 350;
        view.getLayoutParams().height = 350;
        w = view.getLayoutParams().width;
        h = view.getLayoutParams().width;
        d1 = getResources().getDrawable(R.drawable.p1);
        d2 = getResources().getDrawable(R.drawable.p2);
        d3 = getResources().getDrawable(R.drawable.p3);
        view.setBackgroundDrawable(d1);
    }
    public void setWH(int scX , int scY){
        this.scX = scX;
        this.scY = scY;
        iniX = scX/2-w/2;
        iniY = 4*scY/5-h/2;
        view.setX(iniX);
        view.setY(iniY);
    }
    //=====================================
    float nowTx, nowTy;
    float downTx, downTy;
    final int action_none = 0;
    final int action_down = 1;
    final int action_up = 2;
    int moveFlag = action_none;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        nowTx = event.getX();  // 觸控的 X 軸位置
        nowTy = event.getY();  // 觸控的 Y 軸位置
        // 判斷觸控動作
        switch( event.getAction() ) {

            case MotionEvent.ACTION_DOWN:  // 按下
                downTx = nowTx;
                downTy = nowTy;
                if( moveFlag == action_none && nowTx >= view.getX() && nowTx <= w + view.getX()  && nowTy >= view.getY() && nowTy <= h + view.getY()) {
                    moveFlag = action_down;
                }
                break;

            case MotionEvent.ACTION_MOVE:  // 拖曳移動

                if(moveFlag == action_down) {
                    // go
                    view.setX(nowTx-w/2);
                    view.setY(nowTy-h/2);
                }
                break;

            case MotionEvent.ACTION_UP:  // 放開
                if(moveFlag == action_down) {
                    Hit();
                }

                break;
        }
        // TODO Auto-generated method stub
        return true;
    }
    Handler g1 = new Handler();
    Handler g2 = new Handler();
    Handler g3 = new Handler();
    Drawable d1, d2, d3;
    private void Hit() {
        g1.postDelayed( new Runnable() {
            @Override
            public void run() {
                view.setBackgroundDrawable(d2);
                g2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundDrawable(d3);
                        g3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.setBackgroundDrawable(d1);
                                view.setRotation(0);
                                moveFlag = action_none;
                                view.setX(iniX);
                                view.setY(iniY);


                            }
                        },100);
                    }
                },50);
            }
        },50);

    }
  /*  private void Hit() {

        int movingSpeed = 500;

        AnimatorSet moving = new AnimatorSet();

        ObjectAnimator GO_1 = ObjectAnimator.ofFloat(view, "translationX", view.getX(), view.getX()+50);
        ObjectAnimator GO_2 = ObjectAnimator.ofFloat(view, "translationY", view.getY(), view.getY()-100);
        ObjectAnimator GO_3 = ObjectAnimator.ofFloat(view, "rotation", 0, 30f);

        GO_1.setDuration(movingSpeed);
        GO_2.setDuration(movingSpeed);
        GO_3.setDuration(movingSpeed);

        moving.play(GO_1).with(GO_2).with(GO_3).after(0);

        moving.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
                System.out.println("---> onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                System.out.println("---> onAnimationRepeat");

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                System.out.println("---> onAnimationEnd");
                //animator.start();
                moveFlag = action_none;
                view.setX(iniX);
                view.setY(iniY);
                view.setRotation(0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                System.out.println("---> onAnimationCancel");
            }
        });
        moving.setInterpolator(new LinearInterpolator());
        moving.start();
    }*/
}

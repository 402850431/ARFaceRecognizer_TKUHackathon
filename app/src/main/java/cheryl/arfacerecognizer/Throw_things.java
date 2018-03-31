package cheryl.arfacerecognizer;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by L850 on 2018/3/31.
 */

public class Throw_things extends RelativeLayout {
    View view;
    int w, h;
    public Throw_things(Context context) {
        super(context);
        view = new View(context);
        addView(view);
        view.setBackgroundColor(0xffffff00);

        view.getLayoutParams().width = 350;
        view.getLayoutParams().height = 350;
        w = view.getLayoutParams().width;
        h = view.getLayoutParams().width;
    }

    int fromX = 30;
    int fromY = 30;
    private void ThrowIng(){

    }
    //=====================================
    float nowTx, nowTy;
    float downTx, downTy;
    int valTy = 0;
    float deltaTx, deltaTy;
    boolean moveFlag = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        nowTx = event.getX();  // 觸控的 X 軸位置
        nowTy = event.getY();  // 觸控的 Y 軸位置
        Log.e("weiwei","x="+nowTx+",y="+nowTy);
        Log.e("weiwei","event="+event.getAction());
        Log.e("weiwei","moveFlag="+moveFlag);

        // 判斷觸控動作
        switch( event.getAction() ) {

            case MotionEvent.ACTION_DOWN:  // 按下
                downTx = nowTx;
                downTy = nowTy;
                if( nowTx >= view.getX() && nowTx <= w + view.getX()  && nowTy >= view.getY() && nowTy <= h + view.getY()) {
                    moveFlag = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:  // 拖曳移動

               if(moveFlag){
                    // go
                    //deltaTx = nowTx - downTx;
                    //deltaTy = nowTy - downTy;
                    view.setX(nowTx-w/2);
                    view.setY(nowTy-h/2);
                }
                break;

            case MotionEvent.ACTION_UP:  // 放開
                moveFlag = false;
                break;
        }

        // TODO Auto-generated method stub
        return true;
    }

}

package com.sys.blackcat.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangcai on 17-1-7.
 */

public class WaterDropIndicator extends View {

    private int colorSelected;

    private int colorUnselected;

    private int waterDropSize;

    private int waterDropSpace;

    private int waterDropCount = 2;


    public WaterDropIndicator(Context context) {
        this(context, null);
    }

    public WaterDropIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterDropIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        float density = context.getResources().getDisplayMetrics().density;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WaterDropIndicator, defStyleAttr, 0);
        colorSelected = a.getColor(R.styleable.WaterDropIndicator_indicator_selected_color, Color.parseColor("#FF33CC"));
        colorUnselected = a.getColor(R.styleable.WaterDropIndicator_indicator_unselected_color, Color.parseColor("#FF6699"));
        waterDropSize = a.getDimensionPixelSize(R.styleable.WaterDropIndicator_indicator_size, (int) (8 * density));
        waterDropSpace = a.getDimensionPixelOffset(R.styleable.WaterDropIndicator_indicator_space, (int) (12 * density));
        a.recycle();
        initTest();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = waterDropSize + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTest(canvas);
    }

    //Test
    private Path testPath = new Path();
    private Paint testPaint = new Paint();

    int px;
    int py;

    private void initTest() {
        testPaint = new Paint();
        testPaint.setStyle(Paint.Style.FILL);
        testPaint.setAntiAlias(true);
        testPaint.setColor(colorSelected);
        px = getPaddingLeft() + waterDropSize / 2;
        py = getPaddingTop() + waterDropSize / 2;
        testPath = new Path();

        resetTestPath();
    }

    private void resetTestPath() {
        for (int i = 0; i < waterDropCount; i++) {
            testPath.addCircle(getPxByPosition(i), py, waterDropSize / 2, Path.Direction.CCW);
        }
    }


    private void drawLeft(Canvas canvas) {
//        Path left = new Path();
//        left.moveTo(getPxByPosition(0), getPaddingTop());
//        left.quadTo(getPxByPosition(1), py, getPxByPosition(0), py + waterDropSize / 2);
//        canvas.drawPath(left, testPaint);

        // cubic to the right middle
        float centerX = getPxByPosition(0);
        float dotRadius = waterDropSize;
        float halfDotRadius = dotRadius / 2;
        float dotCenterY = py;
        float dotTopY = getPaddingTop();
        float endX1 = centerX + dotRadius + (0.5f * waterDropSpace);
        float endY1 = dotCenterY;
        float controlX1 = centerX + halfDotRadius;
        float controlY1 = dotTopY;
        float controlX2 = endX1;
        float controlY2 = endY1 - halfDotRadius;
        Path unselectedDotLeftPath1 = new Path();
        unselectedDotLeftPath1.moveTo(getPxByPosition(0), py + waterDropSize);
        unselectedDotLeftPath1.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX1, endY1);

        // cubic back to the bottom center
        float dotBottomY = py + waterDropSize;
        float endX2 = centerX;
        float endY2 = dotBottomY;
        controlX1 = endX1;
        controlY1 = endY1 + halfDotRadius;
        controlX2 = centerX + halfDotRadius;
        controlY2 = dotBottomY;
        Path unselectedDotLeftPath = new Path();
        unselectedDotLeftPath.moveTo(getPxByPosition(0), py + waterDropSize);
        unselectedDotLeftPath.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX2, endY2);
        canvas.drawPath(unselectedDotLeftPath1, testPaint);
        // canvas.drawPath(unselectedDotLeftPath,testPaint);
    }

    private void drawRight(Canvas canvas) {
        Path left = new Path();
        left.moveTo(getPxByPosition(1), getPaddingTop());
        left.quadTo(getPxByPosition(0), py, getPxByPosition(1), py + waterDropSize / 2);
        canvas.drawPath(left, testPaint);
    }

    private int getCenterXbyTwoPosition(int p1, int p2) {
        int px1 = getPxByPosition(p1);
        int px2 = getPxByPosition(p2);
        return (px1 + px2) / 2;
    }


    private int getPxByPosition(int position) {
        return px + (waterDropSpace + waterDropSize) * position;
    }


    private void drawTest(Canvas canvas) {
//        canvas.drawPath(testPath, testPaint);
//        drawLeft(canvas);
//      //  drawRight(canvas);

        if (directionJF < 0.5) {
            drawRightCp(canvas);
            drawLeftCp(canvas);
        } else if ( directionJF>=.5&& directionJF <1){
            drawPathRight(canvas);
            drawPathLeft(canvas);
        }else if (directionJF ==1){
            canvas.drawPath(testPath, testPaint);
        }

    }


    private Path pathRight = new Path();

    private void drawRightCp(Canvas canvas) {
        pathRight.rewind();
        float left = getPxByPosition(0) - waterDropSize / 2;
        float top = getPaddingTop();
        float right = getPxByPosition(0) + waterDropSize / 2;
        float bottom = py + waterDropSize / 2;
        RectF mRectF = new RectF(left, top, right, bottom);
        pathRight.moveTo(getPxByPosition(0), bottom);
        pathRight.arcTo(mRectF, 90, 180, true);
        float endX1 = getPxByPosition(0) + waterDropSize / 2 + waterDropSpace * directionJF;
        float endY1 = py;
        float controlX1 = getPxByPosition(0) + waterDropSize / 4;
        float controlY1 = getPaddingTop();
        float controlX2 = endX1;
        float controlY2 = endY1 - waterDropSize / 4;
        pathRight.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = getPxByPosition(0);
        float endY2 = bottom;
        controlX1 = endX1;
        controlY1 = endY1 + waterDropSize / 4;
        controlX2 = getPxByPosition(0) + waterDropSize / 4;
        controlY2 = py + waterDropSize / 2;
        pathRight.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(pathRight, testPaint);
    }

    private Path pathLeft = new Path();

    private void drawLeftCp(Canvas canvas) {
        pathLeft.rewind();
        float left = getPxByPosition(1) - waterDropSize / 2;
        float top = getPaddingTop();
        float right = getPxByPosition(1) + waterDropSize / 2;
        float bottom = py + waterDropSize / 2;
        RectF mRectF = new RectF(left, top, right, bottom);
        pathLeft.moveTo(getPxByPosition(1), bottom);
        pathLeft.arcTo(mRectF, 90, -180, true);
        float endX1 = getPxByPosition(1) - waterDropSize / 2 - waterDropSpace * directionJF;
        float endY1 = py;
        float controlX1 = getPxByPosition(1) - waterDropSize / 4;
        float controlY1 = getPaddingTop();
        float controlX2 = endX1;
        float controlY2 = endY1 - waterDropSize / 4;
        pathLeft.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = getPxByPosition(1);
        float endY2 = bottom;
        controlX1 = endX1;
        controlY1 = endY1 + waterDropSize / 4;
        controlX2 = getPxByPosition(1) - waterDropSize / 4;
        controlY2 = py + waterDropSize / 2;
        pathLeft.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(pathLeft, testPaint);
    }


    private Path drawRight = new Path();

    private void drawPathRight(Canvas canvas) {
        drawRight.rewind();
        // case #3 – Joining neighbour, combined curved

        // adjust the fraction so that it goes from 0.3 -> 1 to produce a more realistic 'join'
        float adjustedFraction = (directionJF - 0.2f) * 1.25f;
        float left = getPxByPosition(0) - waterDropSize / 2;
        float top = getPaddingTop();
        float right = getPxByPosition(0) + waterDropSize / 2;
        float bottom = py + waterDropSize / 2;
        RectF mRectF = new RectF(left, top, right, bottom);
        drawRight.moveTo(getPxByPosition(0), bottom);
        drawRight.arcTo(mRectF, 90, 180, true);

        // bezier to the middle top of the join
        float endX1 = getPxByPosition(0) + waterDropSize / 2 + (waterDropSpace / 2);
        float endY1 = py - (adjustedFraction * waterDropSize / 2);
        float controlX1 = endX1 - (adjustedFraction * waterDropSize / 2);
        float controlY1 = getPaddingTop();
        float controlX2 = endX1 - ((1 - adjustedFraction) * waterDropSize / 2);
        float controlY2 = endY1;
        drawRight.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX1, endY1);
        // bezier to the top right of the join
        float endX2 = getPxByPosition(1);
        float endY2 = getPaddingTop();
        controlX1 = endX1 + ((1 - adjustedFraction) * waterDropSize / 2);
        controlY1 = endY1;
        controlX2 = endX1 + (adjustedFraction * waterDropSize / 2);
        controlY2 = getPaddingTop();
        drawRight.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX2, endY2);
        canvas.drawPath(drawRight, testPaint);
    }

    private Path drawLeft = new Path();

    private void drawPathLeft(Canvas canvas) {
        drawLeft.rewind();
        float adjustedFraction = (directionJF - 0.2f) * 1.25f;
        float left = getPxByPosition(1) - waterDropSize / 2;
        float top = getPaddingTop();
        float right = getPxByPosition(1) + waterDropSize / 2;
        float bottom = py + waterDropSize / 2;
        RectF mRectF = new RectF(left, top, right, bottom);
       // drawLeft.moveTo(getPxByPosition(0), bottom);
        drawLeft.arcTo(mRectF, 270, 180, true);


        // bezier to the middle bottom of the join
        // endX1 stays the same
        float endX1 = getPxByPosition(0) + waterDropSize / 2 + (waterDropSpace / 2);
        float endY1 = py + (adjustedFraction * waterDropSize / 2);
        float controlX1 = endX1 + (adjustedFraction * waterDropSize / 2);
        float controlY1 = bottom;
        float controlX2 = endX1 + ((1 - adjustedFraction) * waterDropSize / 2);
        float controlY2 = endY1;
        drawLeft.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX1, endY1);

        // bezier back to the start point in the bottom left
        float endX2 = getPxByPosition(0);
        float endY2 = bottom;
        controlX1 = endX1 - ((1 - adjustedFraction) * waterDropSize / 2);
        controlY1 = endY1;
        controlX2 = endX1 - (adjustedFraction * waterDropSize / 2);
        controlY2 = endY2;
        drawLeft.cubicTo(controlX1, controlY1,
                controlX2, controlY2,
                endX2, endY2);
        canvas.drawPath(drawLeft, testPaint);
    }


    private float directionJF = 0;

    public void setDirectionJF(float directionJF) {
        this.directionJF = directionJF;
        postInvalidate();
    }
}

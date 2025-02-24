package com.sysdata.htmlspanner.spans;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
import android.util.Log;

import com.sysdata.htmlspanner.HtmlSpanner;
import com.sysdata.htmlspanner.style.Style;
import com.sysdata.htmlspanner.style.StyleValue;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/23/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class HorizontalLineSpan implements LineBackgroundSpan {

    private final Style style;

    public HorizontalLineSpan(Style style) {
        this.style = style;
    }


    @Override
    public void drawBackground(Canvas c, Paint p,
                               int left, int right,
                               int top, int baseline, int bottom,
                               CharSequence text, int start, int end,
                               int lnum) {

        int baseMargin = 0;

        if ( style.getMarginLeft() != null ) {
            StyleValue styleValue = style.getMarginLeft();

            if ( styleValue.getUnit() == StyleValue.Unit.PX ) {
                if ( styleValue.getIntValue() > 0 ) {
                    baseMargin = styleValue.getIntValue();
                }
            } else if ( styleValue.getFloatValue() > 0f ) {
                baseMargin = (int) (styleValue.getFloatValue() * HtmlSpanner.HORIZONTAL_EM_WIDTH);
            }

            //Leave a little bit of room
            baseMargin--;
        }

        if ( baseMargin > 0 ) {
            left = left + baseMargin;
        }

        // Copy values for later resetting
        int originalColor = p.getColor();
        float originalStrokeWidth = p.getStrokeWidth();
        Paint.Style originalStyle = p.getStyle();

        p.setColor(Color.GRAY);
        if (style.getBorderColor() != null ) {
            p.setColor( style.getBorderColor() );
        }

        int strokeWidth;

        if ( style.getBorderWidth() != null && style.getBorderWidth().getUnit() == StyleValue.Unit.PX ) {
            strokeWidth = style.getBorderWidth().getIntValue();
        } else {
            strokeWidth = 1;
        }

        p.setStrokeWidth( strokeWidth );
        right -= strokeWidth;

        p.setStyle(Paint.Style.STROKE);

        Log.d("HorizontalSpan", "Drawing line");

        int center=(bottom+top)/2;
        c.drawLine(left, center, right, center, p);

        p.setColor(originalColor);
        p.setStrokeWidth(originalStrokeWidth);
        p.setStyle(originalStyle);
    }



}


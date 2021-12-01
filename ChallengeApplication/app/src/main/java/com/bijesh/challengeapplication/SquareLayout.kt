package com.bijesh.challengeapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class SquareLayout : LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context,att: AttributeSet) : super(context,att)
    constructor(context: Context,att: AttributeSet,defStyle: Int) : super(context,att,defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
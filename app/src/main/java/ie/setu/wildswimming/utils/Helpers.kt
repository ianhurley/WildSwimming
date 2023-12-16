package ie.setu.wildswimming.utils

import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Transformation
import android.graphics.Color

fun customTransformation() : Transformation =
    RoundedTransformationBuilder()
        .borderColor(Color.WHITE)
        .borderWidthDp(1F)
        .cornerRadiusDp(35F)
        .oval(false)
        .build()
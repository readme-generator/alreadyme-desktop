package dev.yjyoon.alreadyme.ui.value

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

val Montserrat = FontFamily(
    Font("fonts/Montserrat-Bold.ttf", FontWeight.Bold),
    Font("fonts/Montserrat-Regular.ttf", FontWeight.Normal),
    Font("fonts/Montserrat-Medium.ttf", FontWeight.Medium),
    Font("fonts/Montserrat-ExtraLight.ttf", FontWeight.ExtraLight, FontStyle.Normal),
    Font("fonts/Montserrat-ExtraLightItalic.ttf", FontWeight.ExtraLight, FontStyle.Italic)
)


val Typography = Typography(

    defaultFontFamily = Montserrat,

    h1 = TextStyle(
        fontSize = 95.sp,
        fontWeight = FontWeight.Normal,
    ),
    h2 = TextStyle(
        fontSize = 59.sp,
        fontWeight = FontWeight.Normal,
    ),
    h3 = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium
    ),
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium,
    ),
    h5 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    ),
    body1 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
    ),
    overline = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
    )
)

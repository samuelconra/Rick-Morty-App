package com.samuelconra.rickmortyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samuelconra.rickmortyapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val PoppinsFamily = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Thin), // 100
    Font(R.font.poppins_extralight, FontWeight.ExtraLight), //200
    Font(R.font.poppins_light, FontWeight.Light), // 300
    Font(R.font.poppins_regular, FontWeight.Normal), // 400
    Font(R.font.poppins_medium, FontWeight.Medium), // 500
    Font(R.font.poppins_semibold, FontWeight.SemiBold), // 600
    Font(R.font.poppins_bold, FontWeight.Bold), // 700
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold), // 800
    Font(R.font.poppins_black, FontWeight.Black), // 900
)

val PoppinsTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 18.sp,
    )
)
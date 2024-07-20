package com.banqurmisr.chanllenge5.presentaion.componant


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.banqurmisr.chanllenge5.domain.models.Genre

@Composable
fun GenreChip(genre: String) {
    Surface(
        modifier = Modifier.padding(end = 12.dp),
        shape = RoundedCornerShape(12.dp), color = Color(0xFFDCDCDC)
    ) {
        Text(
            text = genre,
            color = Color.DarkGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 8.dp, start = 8.dp, top = 6.dp, bottom = 6.dp)
        )
    }
}
package com.manifestasi.mysporty.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream

//fun ImageProxy.toBitmap(): Bitmap {
//    // Ambil plane YUV (format default CameraX)
//    val yBuffer = planes[0].buffer
//    val uBuffer = planes[1].buffer
//    val vBuffer = planes[2].buffer
//
//    val ySize = yBuffer.remaining()
//    val uSize = uBuffer.remaining()
//    val vSize = vBuffer.remaining()
//
//    val nv21 = ByteArray(ySize + uSize + vSize)
//
//    // Salin data Y ke nv21
//    yBuffer.get(nv21, 0, ySize)
//
//    // Salin data U dan V ke nv21
//    vBuffer.get(nv21, ySize, vSize)
//    uBuffer.get(nv21, ySize + vSize, uSize)
//
//    // Buat YuvImage dari data nv21
//    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
//
//    // Konversi YuvImage ke Bitmap
//    val out = ByteArrayOutputStream()
//    yuvImage.compressToJpeg(android.graphics.Rect(0, 0, this.width, this.height), 100, out)
//    val imageBytes = out.toByteArray()
//    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//}

fun ImageProxy.toBitmap(): Bitmap? {
    val yBuffer = planes[0].buffer
    val uBuffer = planes[1].buffer
    val vBuffer = planes[2].buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)

    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}
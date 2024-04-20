package com.lbtt2801.yamevn.components

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.Transformation

@Composable
fun ImageCustom(
    imageData: Any?, /* Drawable(R.drawable.image) or Url */
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorPlaceholderRes: Int? = null,
    isGrayscale: Boolean = false,
    onImageLoadingState: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onImageSuccessState: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onImageErrorState: ((AsyncImagePainter.State.Error) -> Unit)? = null
) {
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
        .data(imageData)
    if (placeholderRes != null) {
        imageRequestBuilder.placeholder(placeholderRes)
    }
    if (errorPlaceholderRes != null) {
        imageRequestBuilder.error(errorPlaceholderRes)
    }
    if (isGrayscale) {
        val trans = mutableListOf<Transformation>(GrayscaleTransformation())
        imageRequestBuilder.transformations(trans)
    }
    AsyncImage(
        modifier = modifier,
        model = imageRequestBuilder.build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        alignment = alignment,
        onLoading = {
            onImageLoadingState?.invoke(it)
        },
        onSuccess = {
            onImageSuccessState?.invoke(it)
        },
        onError = {
            onImageErrorState?.invoke(it)
        }
    )
}

//--------------------------------  helper coil  ---------------------------------------------------
class GrayscaleTransformation : Transformation {

    override val cacheKey: String = GrayscaleTransformation::class.java.name

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        paint.colorFilter = COLOR_FILTER

        val output = createBitmap(input.width, input.height, input.safeConfig)
        output.applyCanvas {
            drawBitmap(input, 0f, 0f, paint)
        }

        return output
    }

    override fun equals(other: Any?) = other is GrayscaleTransformation

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "GrayscaleTransformation()"

    private companion object {
        val COLOR_FILTER = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
    }
}

val Bitmap.safeConfig: Bitmap.Config
    get() = config ?: Bitmap.Config.ARGB_8888
//--------------------------------------------------------------------------------------------------
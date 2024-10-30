package io.vucos.mobile.presentation.bindingadapters

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("termsAndPrivacyText", "onTermsClicked", "onPrivacyPolicyClicked")
fun TextView.setTermsAndPrivacyText(
    text: String,
    onTermsClicked: View.OnClickListener,
    onPrivacyPolicyClicked: View.OnClickListener
) {
    val spannableString = SpannableString(text)

    val termsStart = text.indexOf("Terms of Service")
    val termsEnd = termsStart + "Terms of Service".length
    val privacyStart = text.indexOf("Privacy Policy")
    val privacyEnd = privacyStart + "Privacy Policy".length

    val termsClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onTermsClicked.onClick(widget)
        }
    }

    val privacyClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onPrivacyPolicyClicked.onClick(widget)
        }
    }

    spannableString.setSpan(termsClickableSpan, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    spannableString.setSpan(privacyClickableSpan, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    this.text = spannableString
    this.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("imageUrl")
fun loadImageButton(view: ImageButton, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}
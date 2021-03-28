package com.karuna.pages.utils

import android.view.View

/**
 * Return the string of two dashes in case it's empty
 */
val String.orDash: String
    get() = if (isBlank()) "--" else this

/**
 * Set view's visibility to [View.VISIBLE]
 */
fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

/**
 * Set view's visibility to [View.GONE]
 */
fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

/**
 * Set view's visibility to [View.INVISIBLE]
 */
fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}
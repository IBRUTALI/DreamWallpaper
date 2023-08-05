package com.example.dreamwallpaper.util

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dreamwallpaper.App
import com.example.dreamwallpaper.di.AppComponent
import com.google.android.material.appbar.MaterialToolbar
import java.util.regex.Pattern

fun MaterialToolbar.setTitle(label: CharSequence?, textView: TextView, arguments: Bundle?) {
    if (label != null) {
        // Fill in the data pattern with the args to build a valid URI
        val title = StringBuffer()
        val fillInPattern = Pattern.compile("\\{(.+?)\\}")
        val matcher = fillInPattern.matcher(label)
        while (matcher.find()) {
            val argName = matcher.group(1)
            if (arguments != null && arguments.containsKey(argName)) {
                matcher.appendReplacement(title, "")
                title.append(arguments.get(argName).toString())
            } else {
                return //returning because the argument required is not found
            }
        }
        matcher.appendTail(title)
        setTitle("")
        textView.text = title
    }
}

fun Fragment.getAppComponent(): AppComponent =
    (requireActivity().application as App).appComponent